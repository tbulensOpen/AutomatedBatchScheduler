package org.tbulens.batchscheduler.service

import org.gmock.WithGMock
import org.junit.Before
import org.junit.Test
import org.quartz.JobDataMap
import org.quartz.JobDetail
import org.quartz.JobExecutionContext
import org.quartz.JobKey
import org.quartz.impl.JobDetailImpl
import org.quartz.impl.JobExecutionContextImpl
import org.tbulens.batchscheduler.util.HttpRequester

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
        mockExecutionContext.getJobDetail().returns(createJobDetail(data))

        play {
            jobRequester.execute(mockExecutionContext)
        }
    }

    private JobDetail createJobDetail(Map<String, String> data) {
        JobKey jobKey = new JobKey(JOB_NAME)
        JobDetail jobDetail = new JobDetailImpl()
        jobDetail.key = jobKey
        jobDetail.jobDataMap = new JobDataMap(data)
        jobDetail
    }
}
