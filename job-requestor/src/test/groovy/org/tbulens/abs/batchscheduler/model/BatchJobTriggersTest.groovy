package org.tbulens.abs.batchscheduler.model

import org.junit.Before
import org.junit.Test
import org.quartz.Trigger
import org.quartz.impl.triggers.CronTriggerImpl


class BatchJobTriggersTest {

    BatchJobCronTriggers batchJobTriggers

    @Before
    void setUp() {
        batchJobTriggers = new BatchJobCronTriggers()
    }

    @Test
    void getAdd() {
        Trigger trigger = new CronTriggerImpl("triggerName")
        batchJobTriggers.addCronTrigger("triggerName", trigger)

        assert trigger == batchJobTriggers.get("triggerName")
    }

}
