package manuscript.management.submission;

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
 * 
 * @author Balazs Kovacs
 *
 */
public interface SubmissionService {

	/**
	 * Get all started, but not submitted manuscripts of the user and all
	 * necessary information what used by submission menu.
	 * 
	 * @return SubmissionPreloadResponse
	 */
	public SubmissionPreloadResponse preload();

	/**
	 * Upload a submission.
	 * 
	 * @return UploadSubmissionResponse
	 */
	public UploadSubmissionResponse uploadSubmission(MultipartFile file);

	/**
	 * Start the submission process. It will generate a submission id and will
	 * store the data of the submission.
	 * 
	 * @return UploadSubmissionResponse
	 */
	public CreateSubmissionResponse createSubmission(CreateSubmissionRequest request) throws SubmissionActionException;

	/**
	 * Save the appropriate not submitted but modified manuscript in the
	 * database.
	 * 
	 * @return SaveSubmissionResponse
	 * @param request
	 */
	public SaveSubmissionResponse saveSubmission(SaveSubmissionRequest request);

	/**
	 * Remove the appropriate not submitted manuscript from the database and all
	 * additional files from the file system.
	 * 
	 * @param submissionId
	 * @return RemoveSubmissionResponse
	 */
	public RemoveSubmissionResponse removeSubmission(RemoveSubmissionRequest request);

	/**
	 * Submit an appropriate Manuscript. The submitted manuscript will be
	 * available for all co-author.
	 * 
	 * @param requesst
	 * @return SubmitSubmissionResponse
	 */
	public SubmitSubmissionResponse submitSubmission(SubmitSubmissionRequest request);

	/**
	 * Get details of co-author.
	 * 
	 * @param email
	 * @return SearchAuthorResponse
	 */
	public SearchAuthorResponse searchAuthor(SearchAuthorRequest request);

	/**
	 * Get information about a manuscript by submissionId.
	 * 
	 * @param submissionId
	 * @return ManuscriptInformationResponse
	 */
}
