package org.tbulens.abs.domain.repository

import org.springframework.stereotype.Repository
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.model.CronExpression

@Repository
class AbsRepositoryImpl implements AbsRepository {

    //todo: Replace with mysql implementation using hibernate.
    List<BatchJob> findAllBatchJobs() {
        BatchJob batchJob2 = createBatchJob("Job2")
        batchJob2.cronExpression = new CronExpression(expression: "0 0 12 * * ?", name: "TriggerJob2")
        BatchJob batchJob3 = createBatchJob("Job3")
        batchJob3.lastRunDate = new Date()
        [createBatchJob("Job1"), batchJob2, batchJob3]
    }

    private BatchJob createBatchJob(String jobName) {
        new BatchJob(jobName: jobName)
    }
}
