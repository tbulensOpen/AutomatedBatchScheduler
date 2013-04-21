package org.tbulens.abs.domain.model

class BatchJobMother {

    static BatchJob create(String jobName) {
        create(jobName: jobName, groupName: "Group1", "* 0 * * ? *")
    }

    static BatchJob create(String jobName, String groupName, String cronExpression) {
        new BatchJob(jobName: jobName, groupName: groupName, cronExpression: new CronExpression(expression: cronExpression) )
    }
}
