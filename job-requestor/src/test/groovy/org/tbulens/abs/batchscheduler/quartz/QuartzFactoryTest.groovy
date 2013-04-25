package org.tbulens.abs.batchscheduler.quartz
import org.junit.Before
import org.junit.Test
import org.quartz.JobKey
import org.quartz.Scheduler
import org.quartz.TriggerKey
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.model.BatchJobMother

class QuartzFactoryTest {

    QuartzFactory quartzFactory
     Scheduler schedule

    @Before
    void setUp() {
        quartzFactory = new QuartzFactory()
        schedule = quartzFactory.createSchedule()
        schedule.clear()
    }

    @Test
    void createSimpleScheduler() {
         assert quartzFactory.createSchedule()
    }

    @Test
    void scheduleJob_CronExpression() {
        BatchJob batchJob = BatchJobMother.create("JobName")
        quartzFactory.scheduleJob(schedule, batchJob)

        assert schedule.getJobDetail(new JobKey(batchJob.jobName, batchJob.groupName))
        assert schedule.getTrigger(new TriggerKey(batchJob.cronExpression.name, batchJob.groupName))
    }

    @Test
    void scheduleJob_simpleTrigger() {
        BatchJob batchJob = BatchJobMother.create("JobName")
        batchJob.cronExpression = null

        quartzFactory.scheduleJob(schedule, batchJob)

        assert schedule.getJobDetail(new JobKey(batchJob.jobName, batchJob.groupName))
        assert schedule.getTrigger(new TriggerKey(batchJob.jobName, batchJob.groupName))
    }

    @Test
    void createCronExpressionNew() {
        assert quartzFactory.createCronTriggerNew("TriggerName", "0 0 12 * * ?")
    }


}
