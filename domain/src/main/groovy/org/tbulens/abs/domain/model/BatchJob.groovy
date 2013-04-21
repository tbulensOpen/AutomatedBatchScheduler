package org.tbulens.abs.domain.model

class BatchJob {
    String jobName
    String groupName
    CronExpression cronExpression  // leave null if one time run
    Date lastRunDate               // if null and cron expression is null, job will be scheduled.

}
