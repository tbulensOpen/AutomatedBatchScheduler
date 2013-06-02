package org.tbulens.abs.domain.model

class BatchJob {
    int batchJobId = -1
    String jobName
    String groupName
    int jobStatusId = JobStatus.WAITING.value()  // cron expressions will not have completed status, after job runs, needs to go back to waiting.
    CronExpression cronExpression        // leave null if one time run
    Date lastRunDate                     // if null and cron expression is null, job will be scheduled.
    List<BatchJobData> batchJobData = []

    private Map<String, Object> data = [:]

//    JobStatus getStatus() {
//        new JobStatus(jobStatusId)
//    }

    Map<String, Object> getData() {
       data.clear()
       batchJobData.each { jobData ->
           data.put(jobData.name, jobData.value)
       }
        data
    }

    void addData(String name, Object value) {
        batchJobData << new BatchJobData(batchJobId: batchJobId, name: name, value: value)
    }
}
