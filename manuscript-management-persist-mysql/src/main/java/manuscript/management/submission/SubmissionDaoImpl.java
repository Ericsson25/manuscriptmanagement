package manuscript.management.submission;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import manuscript.management.bean.Submission;
import manuscript.management.submission.mapper.SubmissionMapper;

@Repository
public class SubmissionDaoImpl implements SubmissionDao {

	private SubmissionMapper submissionMapper;

	public SubmissionDaoImpl(SubmissionMapper submissionMapper) {
		this.submissionMapper = submissionMapper;
	}

	@Override
	public List<Submission> getSubmissions() {
		List<Submission> submissions = new ArrayList<Submission>();
		return submissions;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String createSubmission(Submission submission) {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSubmissionId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public String saveSubmission(Submission submission) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSubmission(String submissionId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void submitSubmission(Submission submission) {
		// TODO Auto-generated method stub

	}
}
