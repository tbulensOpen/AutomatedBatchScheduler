package org.tbulens.batchscheduler.service

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException

class JobRequester implements Job {

    void execute(JobExecutionContext context) throws JobExecutionException {
        println "Hello -- " + context.getJobDetail().getKey().name
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
