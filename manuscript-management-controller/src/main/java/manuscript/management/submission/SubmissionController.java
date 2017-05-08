package manuscript.management.submission;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

/**
 * 
 * @author Balazs Kovacs
 *
 */
@RestController
@RequestMapping("/submission")
public class SubmissionController {

	@Autowired
	private SubmissionService submissionService;

	@RequestMapping("/preload")
	public SubmissionPreloadResponse preload() {
		return submissionService.preload();
	}

	@RequestMapping("/upload")
	public UploadSubmissionResponse uploadSubmission(@RequestParam("file") MultipartFile file) {
		return submissionService.uploadSubmission(file);
	}

	@RequestMapping("/create")
	public CreateSubmissionResponse createSubmission(@RequestBody CreateSubmissionRequest request) {
		return null;

	}

	@RequestMapping("/save")
	public SaveSubmissionResponse saveSubmission(@RequestBody SaveSubmissionRequest request) {
		return null;

	}

	@RequestMapping("/remove")
	public RemoveSubmissionResponse removeSubmission(@RequestBody RemoveSubmissionRequest request) {
		return null;

	}

	@RequestMapping("/submit")
	public SubmitSubmissionResponse submitSubmission(@RequestBody @Valid SubmitSubmissionRequest request) {
		return null;

	}

	@RequestMapping("/searchauthor")
	public SearchAuthorResponse searchAuthor(@RequestBody SearchAuthorRequest request) {
		return null;

	}
}
