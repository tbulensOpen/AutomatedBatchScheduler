package org.tbulens.abs.batchscheduler.service

import org.gmock.WithGMock
import org.junit.Before
import org.junit.Test
import org.quartz.Scheduler
import org.quartz.impl.StdSchedulerFactory
import org.tbulens.abs.batchscheduler.model.BatchJobTriggers
import org.tbulens.abs.batchscheduler.quartz.BatchJobTriggersFactory
import org.tbulens.abs.batchscheduler.quartz.QuartzFactory
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.model.BatchJobMother
import org.tbulens.abs.domain.model.JobStatus
import org.tbulens.abs.domain.repository.AbsRepository

@WithGMock
class BatchJobScheduleServiceTest {

    BatchJobScheduleService batchJobScheduleService
    AbsRepository mockAbsRepository
    QuartzFactory mockQuartzFactory
    BatchJobTriggersFactory mockBatchJobTriggersFactory

    @Before
    void setUp() {
        batchJobScheduleService = createBatchJobScheduleService()
    }

    @Test
    void load() {
        BatchJobTriggers batchJobTriggers = new BatchJobTriggers()
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler()

        List<BatchJob> batchJobs = createBatchJobs()
        mockAbsRepository.findAllBatchJobs().returns(batchJobs)
        mockBatchJobTriggersFactory.create(batchJobs).returns(batchJobTriggers)
        mockQuartzFactory.createSchedule().returns(scheduler)

        String jobName = BatchJobScheduleService.BATCH_JOB_SCHEDULER
        BatchJob batchJobScheduler = new BatchJob(jobName: jobName, jobStatusId: JobStatus.STOP.value())
        mockAbsRepository.findBatchJobByName(BatchJobScheduleService.BATCH_JOB_SCHEDULER).returns(batchJobScheduler)

        play {
            batchJobScheduleService.load()
        }
    }

    private List<BatchJob> createBatchJobs() {
        def batchJobs = []
        batchJobs << createBatchJob("Job1", "Group1", "* 0 * * ? *")
        batchJobs << createBatchJob("Job2", "Group1", "* 0 * * ? *")
        batchJobs << createBatchJob("Job3", "Group2", null)
        batchJobs
    }

    private BatchJob createBatchJob(String jobName, String jobGroup, String cronExpression) {
        BatchJobMother.create(jobName, jobGroup, cronExpression)
    }

    private BatchJobScheduleService createBatchJobScheduleService() {
        mockAbsRepository = mock(AbsRepository)
        mockQuartzFactory = mock(QuartzFactory)
        mockBatchJobTriggersFactory = mock(BatchJobTriggersFactory)

        new BatchJobScheduleService(absRepository: mockAbsRepository, quartzFactory: mockQuartzFactory, batchJobTriggersFactory: mockBatchJobTriggersFactory)
    }

}
