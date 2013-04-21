package org.tbulens.abs.batchscheduler.quartz

import org.junit.Before
import org.junit.Test
import org.tbulens.abs.domain.model.BatchJobMother


class QuartzFactoryTest {

    QuartzFactory quartzFactory

    @Before
    void setUp() {
        quartzFactory = new QuartzFactory()
    }

    @Test
    void createSimpleScheduler() {
         assert quartzFactory.createScheduler()
    }

    @Test
    void scheduleJob() {
        quartzFactory.scheduleJob(quartzFactory.createScheduler(), BatchJobMother.create("JobName"))
    }
}
