package org.tbulens.abs.batchscheduler.service

import org.quartz.Job
import org.quartz.JobDetail
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.tbulens.abs.batchscheduler.util.HttpRequester

class JobRequester implements Job {

    HttpRequester httpRequester

    void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail()
        String jobName = jobDetail.getKey().name

        println "Job Executing = "  + jobName

        def data = jobDetail.jobDataMap.getWrappedMap()
        httpRequester.send(jobName, data)
    }
}
