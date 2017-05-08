package manuscript.module.manuscript.management.exception;

public class SubmissionActionException extends RuntimeException {

	private String errorMessage;

	public SubmissionActionException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
