package org.tbulens.abs.domain.model

class BatchJobMother {

    static BatchJob create(String jobName) {
        create(jobName, "Group1", "0 0 12 * * ?")
    }

    static BatchJob create(String jobName, String groupName, String cronExpression) {
        new BatchJob(jobName: jobName, groupName: groupName, cronExpression: new CronExpression(expression: cronExpression, name: jobName))
    }

    static BatchJob createSimpleJob(String jobName) {
        new BatchJob(jobName: jobName, groupName: "Group1", cronExpression: null)
    }
}
