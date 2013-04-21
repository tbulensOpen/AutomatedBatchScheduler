package org.tbulens.abs.batchscheduler.learning

import org.junit.Test
import org.quartz.CronScheduleBuilder
import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.impl.StdSchedulerFactory
import org.tbulens.abs.batchscheduler.service.JobRequester

import static org.quartz.JobBuilder.newJob
import static org.quartz.SimpleScheduleBuilder.simpleSchedule
import static org.quartz.TriggerBuilder.newTrigger


class QuartzTriggerTest {


    @Test
    void quartzScheduler() {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        scheduleJob(scheduler, "TestJob1", "TestTrigger", "TestJobGroup")

        Thread.sleep(3000)
        scheduler.shutdown();
        assert true
    }

    private void scheduleJob(Scheduler scheduler, String jobName, String triggerName, String jobGroup) {
        JobDetail job = createJob(jobName, jobGroup)
        Trigger trigger = createSimpleTrigger(triggerName, jobGroup)
        scheduler.scheduleJob(job, trigger);
    }


    private JobDetail createJob(String jobName, String jobGroup) {
        newJob(JobRequester.class)
                .withIdentity(jobName, jobGroup)
                .build()
    }

    private Trigger createSimpleTrigger(String triggerName, String jobGroup) {
        Trigger trigger = newTrigger()
                .withIdentity(triggerName, jobGroup)
                .startNow()
                .withSchedule(simpleSchedule()
                .withIntervalInSeconds(40)
                .repeatForever())
                .build()
    }

    private Trigger createCronTrigger(String cronExpression) {
        CronScheduleBuilder.cronSchedule(cronExpression).build()
    }

}
