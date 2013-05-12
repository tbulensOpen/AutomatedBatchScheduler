package org.tbulens.abs.batchscheduler.service
import org.quartz.Job
import org.quartz.JobDetail
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.tbulens.abs.batchscheduler.util.HttpRequestor

class JobRequester implements Job {
    HttpRequestor httpRequestor = new HttpRequestor()

    void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail()
        String jobName = jobDetail.getKey().name

        def data = jobDetail.jobDataMap.getWrappedMap()

        httpRequestor().send(jobName, data)
    }
}
