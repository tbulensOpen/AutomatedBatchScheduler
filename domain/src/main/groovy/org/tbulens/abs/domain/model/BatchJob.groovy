package org.tbulens.abs.domain.model

class BatchJob {
    String jobName
    String groupName
    CronExpression cronExpression  // leave null if one time run
    Date lastRunDate               // if null and cron expression is null, job will be scheduled.
    JobStatus status                  // cron expressions will not have completed status, after job runs, needs to go back to waiting.
    Map<String, Object> data = [:]
}
