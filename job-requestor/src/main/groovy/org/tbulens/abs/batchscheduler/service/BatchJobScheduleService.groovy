package org.tbulens.abs.batchscheduler.service

import org.quartz.Scheduler
import org.tbulens.abs.batchscheduler.quartz.QuartzFactory
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.repository.AbsRepository

class BatchJobScheduleService {

    AbsRepository absRepository
    QuartzFactory quartzFactory

    void load() {
        List<BatchJob> batchJobs = absRepository.findAllBatchJobs()
        Scheduler scheduler = quartzFactory.createScheduler()
    }
}
