package manuscript.management.submission;

import java.util.List;

import manuscript.management.bean.Submission;

public interface SubmissionDao {

	public List<Submission> getSubmissions();

	public String createSubmission(Submission submission);

	public String saveSubmission(Submission submission);

	public void removeSubmission(String submissionId);

	public void submitSubmission(Submission submission);
}
