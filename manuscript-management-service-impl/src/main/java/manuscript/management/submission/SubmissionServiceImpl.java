/**
 * 
 */
package manuscript.management.submission;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import manuscript.management.request.CreateSubmissionRequest;
import manuscript.management.request.RemoveSubmissionRequest;
import manuscript.management.request.SaveSubmissionRequest;
import manuscript.management.request.SearchAuthorRequest;
import manuscript.management.request.SubmitSubmissionRequest;
import manuscript.management.response.CreateSubmissionResponse;
import manuscript.management.response.RemoveSubmissionResponse;
import manuscript.management.response.SaveSubmissionResponse;
import manuscript.management.response.SearchAuthorResponse;
import manuscript.management.response.SubmissionPreloadResponse;
import manuscript.management.response.SubmitSubmissionResponse;
import manuscript.management.response.UploadSubmissionResponse;
import manuscript.module.manuscript.management.exception.SubmissionActionException;

/**
 * @author Balazs Kovacs
 *
 */
@Service
public class SubmissionServiceImpl implements SubmissionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubmissionServiceImpl.class);

	@Autowired(required = false)
	@Qualifier(value = "saveFilesFolder")
	private String saveFilesFolder;

	@Autowired
	private SubmissionDao submissionDao;

	public SubmissionServiceImpl(SubmissionDao submissionDao) {
		this.submissionDao = submissionDao;
	}

	@Override
	public SubmissionPreloadResponse preload() {
		SubmissionPreloadResponse response = new SubmissionPreloadResponse();
		response.setSubmission(submissionDao.getSubmissions());
		return response;
	}

	@Override
	public UploadSubmissionResponse uploadSubmission(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateSubmissionResponse createSubmission(CreateSubmissionRequest request) throws SubmissionActionException {
		CreateSubmissionResponse response = new CreateSubmissionResponse();

		if (request.getSavedFileUrl() == null || request.getSavedFileUrl().isEmpty()) {
			throw new SubmissionActionException("The File URL must not be null!");
		}

		String submissionId = null;

		try {
			submissionId = submissionDao.createSubmission(request.getSubmission());
		} catch (Exception e) {
			LOGGER.debug("Exception occured during createSubmission: {}", e);
			throw new SubmissionActionException(
					"Exception occured during create the submission. Please try again later. Thank you for your understanding. ");
		}

		response.setSubmissionId(submissionId);
		response.setSuccessMessage("Your new Submission ID is " + response.getSubmissionId());
		return response;
	}

	@Override
	public SaveSubmissionResponse saveSubmission(SaveSubmissionRequest request) {
		SaveSubmissionResponse response = new SaveSubmissionResponse();

		String submissionId = null;

		try {
			submissionId = submissionDao.saveSubmission(request.getSubmission());
		} catch (Exception e) {
			LOGGER.debug("Exception occured during saveSubmission: {}", e);
			throw new SubmissionActionException(
					"Exception occured during save the submission. Please try again later. Thank you for your understanding. ");
		}

		response.setSubmissionId(submissionId);
		response.setSuccessMessage("Your submission with " + submissionId + " ID has been saved successfully.");
		return response;
	}

	@Override
	public RemoveSubmissionResponse removeSubmission(RemoveSubmissionRequest request) {
		RemoveSubmissionResponse response = new RemoveSubmissionResponse();

		if (request.getSubmissionId() == null || request.getSubmissionId().isEmpty()) {
			LOGGER.debug("Invalid request in removeSubmission method: {}", request);
			throw new SubmissionActionException("Invalid request");
		}

		try {
			submissionDao.removeSubmission(request.getSubmissionId());
		} catch (Exception e) {
			LOGGER.debug("Exception occured during removeSubmission: {}", e);
			throw new SubmissionActionException(
					"Can not remove this submission. Please try again later. Thank you for your understanding. ");
		}

		response.setSuccessMessage("Your submission has been sucessfully removed.");
		return response;
	}

	@Override
	public SubmitSubmissionResponse submitSubmission(SubmitSubmissionRequest request) {
		SubmitSubmissionResponse response = new SubmitSubmissionResponse();

		try {
			submissionDao.submitSubmission(request.getSubmission());
		} catch (Exception e) {
			LOGGER.debug("Exception occured during submitSubmission method: {}", e);
			throw new SubmissionActionException("Please try again later.");
		}

		response.setSuccessMessage("You have been sucessfully submit your submission with submission id "
				+ request.getSubmission().getSubmissionId());
		return response;
	}

	@Override
	public SearchAuthorResponse searchAuthor(SearchAuthorRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
