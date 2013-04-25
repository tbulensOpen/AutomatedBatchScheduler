package org.tbulens.abs.batchscheduler.quartz

import org.gmock.WithGMock
import org.junit.Before
import org.junit.Test
import org.quartz.Trigger
import org.quartz.impl.triggers.CronTriggerImpl
import org.quartz.impl.triggers.SimpleTriggerImpl
import org.tbulens.abs.batchscheduler.model.BatchJobCronTriggers
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.model.BatchJobMother

@WithGMock
class BatchJobTriggersFactoryTest {

    BatchJobTriggersFactory batchJobTriggersFactory
    QuartzFactory mockQuartzFactory

    @Before
    void setUp() {
        mockQuartzFactory = mock(QuartzFactory)
        batchJobTriggersFactory = new BatchJobTriggersFactory(quartzFactory: mockQuartzFactory)
    }

    @Test
    void create_NoJobs() {
        play {
            BatchJobCronTriggers triggers = batchJobTriggersFactory.create([])
            assert triggers.cronTriggers.isEmpty()
            assert triggers.simpleTriggers.isEmpty()
        }
    }

    @Test
    void create_WithCronExpression() {
        BatchJob batchJob = BatchJobMother.create("jobName")
        Trigger cronTrigger = new CronTriggerImpl()

        mockQuartzFactory.createCronTriggerNew(batchJob.cronExpression.name, batchJob.cronExpression.expression).returns(cronTrigger)

        play {
            BatchJobCronTriggers triggers = batchJobTriggersFactory.create([batchJob])
            assert triggers.cronTriggers.size() == 1
            assert triggers.cronTriggers.get(batchJob.cronExpression.name) == cronTrigger
            assert triggers.simpleTriggers.isEmpty()
        }
    }

    @Test
    void create_WithSimpleTrigger() {
        BatchJob batchJob = BatchJobMother.createSimpleJob("jobName")
        Trigger trigger = new SimpleTriggerImpl()

        mockQuartzFactory.createSimpleTrigger(batchJob.jobName, batchJob.groupName).returns(trigger)

        play {
            BatchJobCronTriggers triggers = batchJobTriggersFactory.create([batchJob])
            assert triggers.cronTriggers.size() == 0
            assert triggers.simpleTriggers.get(batchJob.jobName) == trigger
            assert triggers.simpleTriggers.size() == 1
        }
    }
}
