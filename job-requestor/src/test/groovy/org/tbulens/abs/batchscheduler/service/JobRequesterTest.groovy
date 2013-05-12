package org.tbulens.abs.batchscheduler.service

import org.gmock.WithGMock
import org.junit.Before
import org.junit.Test
import org.quartz.JobDetail
import org.quartz.JobExecutionContext
import org.tbulens.abs.batchscheduler.util.HttpRequestor

@WithGMock
class JobRequesterTest {

    static final String JOB_NAME = "JobName"
    JobRequester jobRequester
    HttpRequestor mockHttpRequestor
    JobExecutionContext mockExecutionContext

    @Before
    void setUp() {
        mockHttpRequestor = mock(HttpRequestor)
        mockExecutionContext = mock(JobExecutionContext)
        jobRequester = new JobRequester()
        jobRequester.httpRequestor = mockHttpRequestor
    }

    @Test
    void testExecute() {
        Map<String, Object> data = [version: "1.0"]

        JobDetail jobDetail = JobDetailMother.create(JOB_NAME, data)
//        mockHttpRequestor.send(JOB_NAME, jobDetail.jobDataMap.getWrappedMap())
//        mockExecutionContext.getJobDetail().returns(jobDetail)

        play {
            //todo Fix
//            jobRequester.execute(mockExecutionContext)
        }
    }
}
