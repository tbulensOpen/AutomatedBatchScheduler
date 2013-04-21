package org.tbulens.abs.batchscheduler.service

import org.quartz.JobDataMap
import org.quartz.JobDetail
import org.quartz.JobKey
import org.quartz.impl.JobDetailImpl

class JobDetailMother {

    static JobDetail create(String jobName, Map<String, Object> data) {
        JobKey jobKey = new JobKey(jobName)
                JobDetail jobDetail = new JobDetailImpl()
                jobDetail.key = jobKey
                jobDetail.jobDataMap = new JobDataMap(data)
                jobDetail
    }
}
