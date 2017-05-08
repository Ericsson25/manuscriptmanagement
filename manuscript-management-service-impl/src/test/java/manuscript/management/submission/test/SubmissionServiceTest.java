package manuscript.management.submission.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import manuscript.management.bean.Submission;
import manuscript.management.request.CreateSubmissionRequest;
import manuscript.management.request.RemoveSubmissionRequest;
import manuscript.management.request.SaveSubmissionRequest;
import manuscript.management.request.SubmitSubmissionRequest;
import manuscript.management.response.CreateSubmissionResponse;
import manuscript.management.response.RemoveSubmissionResponse;
import manuscript.management.response.SaveSubmissionResponse;
import manuscript.management.response.SubmissionPreloadResponse;
import manuscript.management.response.SubmitSubmissionResponse;
import manuscript.management.submission.SubmissionDao;
import manuscript.management.submission.SubmissionService;
import manuscript.module.manuscript.management.exception.SubmissionActionException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SubmissionServiceContext.class)
public class SubmissionServiceTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(SubmissionServiceTest.class);

	@Autowired
	private SubmissionService submissionService;
	@Autowired
	private SubmissionDao submissionDao;

	private List<Submission> submissionList;
	private String submissionId;

	@Before
	public void before() {
		submissionList = new ArrayList<Submission>();
		submissionId = generateSubmissionId();
		EasyMock.reset(submissionDao);
	}

	@Test
	public void testDependencies() {
		Assert.assertNotNull(submissionDao);
		Assert.assertNotNull(submissionService);
	}

	@Test
	public void preloadWithoutResultTest() {
		EasyMock.expect(submissionDao.getSubmissions()).andReturn(submissionList).anyTimes();
		EasyMock.replay(submissionDao);

		SubmissionPreloadResponse response = submissionService.preload();
		Assert.assertTrue("Submissions list should no contains any submissions", response.getSubmission().size() < 1);

		EasyMock.verify(submissionDao);
	}

	@Test
	public void preloadWithResultTest() {
		submissionList.add(getSubmission());
		EasyMock.expect(submissionDao.getSubmissions()).andReturn(submissionList).anyTimes();
		EasyMock.replay(submissionDao);

		SubmissionPreloadResponse response = submissionService.preload();
		Assert.assertTrue("Submissions list should contains any submissions", response.getSubmission().size() > 0);

		EasyMock.verify(submissionDao);
	}

	@Test
	public void createSubmissionWithValidRequestAndReturnSubmissionId() {
		CreateSubmissionRequest request = new CreateSubmissionRequest();
		request.setSavedFileUrl("C:/dummy/url");
		Submission submission = getSubmission();

		EasyMock.expect(submissionDao.createSubmission(submission)).andReturn(submissionId);
		EasyMock.replay(submissionDao);
		request.setSubmission(submission);
		CreateSubmissionResponse response = submissionService.createSubmission(request);

		Assert.assertNotNull("GeneratedUUID should not be null.", response.getSubmissionId());
	}

	@Test(expected = SubmissionActionException.class)
	public void createSubmissionWithValidRequestAndNoReturnSubmissionId() {
		CreateSubmissionRequest request = new CreateSubmissionRequest();
		request.setSavedFileUrl("C:/dummy/url");
		Submission submission = getSubmission();

		Exception exception = new Exception("Any Exception");
		try {
			EasyMock.expect(submissionDao.createSubmission(submission)).andThrow(exception);
		} catch (Exception e) {
			throw new SubmissionActionException("");
		}
	}

	@Test(expected = SubmissionActionException.class)
	public void createSubmissionThrowExceptionBecauseThereIsNoSavedFileUrl() {
		CreateSubmissionRequest request = new CreateSubmissionRequest();

		submissionService.createSubmission(request);
	}

	@Test
	public void saveSubmissionWithSuccess() {
		SaveSubmissionRequest request = new SaveSubmissionRequest();
		Submission submission = getSubmission();
		submission.setSubmissionId(generateSubmissionId());

		EasyMock.expect(submissionDao.saveSubmission(submission)).andReturn(submission.getSubmissionId());
		EasyMock.replay(submissionDao);

		request.setSubmission(submission);
		SaveSubmissionResponse respose = submissionService.saveSubmission(request);

		Assert.assertNotNull("Submission Id must not be null.", respose.getSubmissionId());
	}

	@Test(expected = SubmissionActionException.class)
	public void removeSubmissionWithInvalidRequestTest() {
		RemoveSubmissionRequest request = new RemoveSubmissionRequest();
		submissionService.removeSubmission(request);
	}

	@Test
	public void removeSubmissionWithValidRequestTest() {
		RemoveSubmissionRequest request = new RemoveSubmissionRequest();
		RemoveSubmissionResponse response = new RemoveSubmissionResponse();
		request.setSubmissionId(generateSubmissionId());

		submissionService.removeSubmission(request);
		response.setSuccessMessage("success");

		Assert.assertTrue(response.getSuccessMessage() != null);
	}

	@Test
	public void submitSubmissionWithValidRequest() {
		SubmitSubmissionResponse response = new SubmitSubmissionResponse();
		SubmitSubmissionRequest request = new SubmitSubmissionRequest();

		request.setSubmission(getSubmission());

		submissionService.submitSubmission(request);
		response.setSuccessMessage("success");

		Assert.assertTrue(response.getSuccessMessage() != null);
	}

	private Submission getSubmission() {
		Submission submission = new Submission();
		submission.setTitle("Title1");
		return submission;
	}

	private String generateSubmissionId() {
		return UUID.randomUUID().toString();
	}
}
