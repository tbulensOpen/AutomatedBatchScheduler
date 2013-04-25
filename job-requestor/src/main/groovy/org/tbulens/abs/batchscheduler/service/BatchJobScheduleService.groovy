package org.tbulens.abs.batchscheduler.service

import org.quartz.Scheduler
import org.tbulens.abs.batchscheduler.model.BatchJobTriggers
import org.tbulens.abs.batchscheduler.quartz.BatchJobTriggersFactory
import org.tbulens.abs.batchscheduler.quartz.QuartzFactory
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.repository.AbsRepository

class BatchJobScheduleService {

    AbsRepository absRepository
    QuartzFactory quartzFactory
    BatchJobTriggersFactory batchJobTriggersFactory

    void load() {
        List<BatchJob> batchJobs = absRepository.findAllBatchJobs()
        BatchJobTriggers batchJobCronTriggers = batchJobTriggersFactory.create(batchJobs)
        Scheduler scheduler = quartzFactory.createSchedule()
    }
}
