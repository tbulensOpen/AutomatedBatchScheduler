package org.tbulens.abs.batchscheduler.service

import org.gmock.WithGMock
import org.junit.Before
import org.junit.Test
import org.quartz.JobExecutionContext
import org.tbulens.abs.batchscheduler.util.HttpRequester

@WithGMock
class JobRequesterTest {

    static final String JOB_NAME = "JobName"
    JobRequester jobRequester
    HttpRequester mockHttpRequester
    JobExecutionContext mockExecutionContext

    @Before
    void setUp() {
        mockHttpRequester = mock(HttpRequester)
        mockExecutionContext = mock(JobExecutionContext)
        jobRequester = new JobRequester(httpRequester: mockHttpRequester)
    }

    @Test
    void testExecute() {
        Map<String, Object> data = [version: "1.0"]

        mockHttpRequester.send(JOB_NAME, data)
        mockExecutionContext.getJobDetail().returns( JobDetailMother.create(JOB_NAME, data))

        play {
            jobRequester.execute(mockExecutionContext)
        }
    }
}