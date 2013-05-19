package org.tbulens.abs.batchscheduler.service

import org.quartz.JobDetail
import org.quartz.Scheduler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.tbulens.abs.batchscheduler.model.BatchJobTriggers
import org.tbulens.abs.batchscheduler.quartz.BatchJobTriggersFactory
import org.tbulens.abs.batchscheduler.quartz.QuartzFactory
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.model.JobStatus
import org.tbulens.abs.domain.repository.AbsRepository

@Service
class BatchJobScheduleService {

    static final String BATCH_JOB_SCHEDULER = "BatchJobScheduler"

    @Autowired AbsRepository absRepository
    @Autowired QuartzFactory quartzFactory
    @Autowired BatchJobTriggersFactory batchJobTriggersFactory

    Scheduler scheduler

    void load() {
        List<BatchJob> batchJobs = absRepository.findAllBatchJobs()
        BatchJobTriggers batchJobCronTriggers = batchJobTriggersFactory.create(batchJobs)

        scheduler = quartzFactory.createSchedule()

        loadCronJobs(batchJobCronTriggers, scheduler)
        loadSimpleJobs(batchJobCronTriggers, scheduler)

        runUntilTimeToStop()
    }

    private runUntilTimeToStop() {
        startSchedule()

        boolean stop = false
        while (!stop) {
            Thread.sleep(10000)
            BatchJob scheduler = absRepository.findBatchJobByName(BATCH_JOB_SCHEDULER)
            stop = (scheduler.status.value() == JobStatus.STOP.value())
        }
        stopSchedule()
    }

    private void startSchedule() {
        scheduler.start()
    }

    private void stopSchedule() {
        scheduler.shutdown()
    }

    private void loadCronJobs(BatchJobTriggers batchJobCronTriggers, scheduler) {
        batchJobCronTriggers.cronJobs.each { batchJob ->
            JobDetail job = quartzFactory.createJob(batchJob)
            scheduler.scheduleJob(job, batchJobCronTriggers.getCronTrigger(batchJob.cronExpression.name))
        }
    }

    private void loadSimpleJobs(BatchJobTriggers batchJobCronTriggers, scheduler) {
        batchJobCronTriggers.simpleJobs.each { batchJob ->
            JobDetail job = quartzFactory.createJob(batchJob)
            scheduler.scheduleJob(job, batchJobCronTriggers.getSimpleTrigger(batchJob.jobName))
        }
    }


}
