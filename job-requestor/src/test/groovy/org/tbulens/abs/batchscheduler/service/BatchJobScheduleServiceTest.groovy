package org.tbulens.abs.batchscheduler.service

import org.gmock.WithGMock
import org.junit.Before
import org.junit.Test
import org.quartz.Scheduler
import org.quartz.impl.StdSchedulerFactory
import org.tbulens.abs.batchscheduler.quartz.QuartzFactory
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.model.BatchJobMother
import org.tbulens.abs.domain.repository.AbsRepository


@WithGMock
class BatchJobScheduleServiceTest {

    BatchJobScheduleService batchJobScheduleService
    AbsRepository mockAbsRepository
    QuartzFactory mockQuartzFactory

    @Before
    void setUp() {
        mockAbsRepository = mock(AbsRepository)
        mockQuartzFactory = mock(QuartzFactory)

        batchJobScheduleService = new BatchJobScheduleService(absRepository: mockAbsRepository, quartzFactory: mockQuartzFactory)
    }

    @Test
    void load() {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler()
        mockAbsRepository.findAllBatchJobs().returns(createBatchJobs())
        mockQuartzFactory.createSchedule().returns(scheduler)

        play {
          batchJobScheduleService.load()
        }
    }

    private def createBatchJobs() {
        def batchJobs = []
        batchJobs << createBatchJob("Job1", "Group1", "* 0 * * ? *")
        batchJobs << createBatchJob("Job2", "Group1", "* 0 * * ? *")
        batchJobs << createBatchJob("Job3", "Group2", null)
        batchJobs
    }

    private BatchJob createBatchJob(String jobName, String jobGroup, String cronExpression) {
        BatchJobMother.create(jobName, jobGroup, cronExpression)
    }
}
