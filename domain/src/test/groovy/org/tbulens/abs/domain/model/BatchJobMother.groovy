package org.tbulens.abs.domain.model

class BatchJobMother {

    static BatchJob create(String jobName) {
        new BatchJob(jobName: jobName, groupName: "Group1")
    }
}
