package manuscript.management.submission.test;

import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import manuscript.management.submission.SubmissionDao;
import manuscript.management.submission.SubmissionService;
import manuscript.management.submission.SubmissionServiceImpl;
import manuscript.test.annotation.IgnoreTestContext;

@IgnoreTestContext
@ContextConfiguration
public class SubmissionServiceContext {

	@Bean
	public SubmissionService getSubmissionService() {
		return new SubmissionServiceImpl(getSubmissionDao());
	}

	@Bean
	public SubmissionDao getSubmissionDao() {
		return EasyMock.createStrictMock(SubmissionDao.class);
	}
}
