package org.tbulens.abs.batchscheduler.model

import org.junit.Before
import org.junit.Test
import org.quartz.Trigger
import org.quartz.impl.triggers.CronTriggerImpl
import org.quartz.impl.triggers.SimpleTriggerImpl
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.model.BatchJobMother


class BatchJobTriggersTest {

    BatchJobTriggers batchJobTriggers

    @Before
    void setUp() {
        batchJobTriggers = new BatchJobTriggers()
    }

    @Test
    void getCronTrigger() {
        Trigger trigger = new CronTriggerImpl("triggerName")
        BatchJob batchJob = BatchJobMother.create("batchJobName")
        batchJobTriggers.addCronTrigger(batchJob, trigger)

        assert trigger == batchJobTriggers.getCronTrigger(batchJob.cronExpression.name)
        assert batchJob == batchJobTriggers.cronJobs.get(0)
    }

    @Test
    void getSimpleTrigger() {
        Trigger trigger = new SimpleTriggerImpl("triggerName")
        BatchJob batchJob = BatchJobMother.create("batchJobName")
        batchJobTriggers.addCronTrigger(batchJob, trigger)

        assert trigger == batchJobTriggers.getCronTrigger(batchJob.jobName)
        assert batchJob == batchJobTriggers.cronJobs.get(0)
    }

}
