package org.tbulens.abs.domain.repository

import org.springframework.stereotype.Repository
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.model.CronExpression
import org.tbulens.abs.domain.model.JobStatus

@Repository
class AbsRepositoryImpl implements AbsRepository {

    //todo: Replace with mysql implementation using hibernate.
    List<BatchJob> findAllBatchJobs() {
        BatchJob batchJob2 = createBatchJob("job2")
        batchJob2.cronExpression = new CronExpression(expression: "0/2 * * * * ?", name: "TriggerJob2")
        BatchJob batchJob3 = createBatchJob("job3")
        batchJob3.lastRunDate = new Date()
//        [createBatchJob("job1"), batchJob2, batchJob3]
        BatchJob batchJob1 = createBatchJob("job1")
        batchJob1.data =[key1: "Hello", key2: "GoodBye"]
        [batchJob1]
    }

    BatchJob findBatchJobByName(String batchJob) {
        new BatchJob(jobName: batchJob, jobStatusId: JobStatus.STOP.value())
    }

    private BatchJob createBatchJob(String jobName) {
        new BatchJob(jobName: jobName)
    }
}
