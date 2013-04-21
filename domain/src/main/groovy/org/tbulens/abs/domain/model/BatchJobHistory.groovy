package org.tbulens.abs.domain.model


class BatchJobHistory {
    long id
    String jobName
    String groupName
    CronExpression cronExpression
    Date runDate
    JobStatus status
}
