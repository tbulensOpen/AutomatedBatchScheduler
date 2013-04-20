package org.tbulens.batchscheduler.learning

import org.junit.Test
import static org.quartz.JobBuilder.*
import static org.quartz.TriggerBuilder.*
import static org.quartz.SimpleScheduleBuilder.*
import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.impl.StdSchedulerFactory
import org.tbulens.batchscheduler.service.JobRequester


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
        Trigger trigger = createTrigger(triggerName, jobGroup)
        scheduler.scheduleJob(job, trigger);
    }


    private JobDetail createJob(String jobName, String jobGroup) {
        newJob(JobRequester.class)
                .withIdentity(jobName, jobGroup)
                .build()
    }

    private Trigger createTrigger(String triggerName, String jobGroup) {
        Trigger trigger = newTrigger()
                .withIdentity(triggerName, jobGroup)
                .startNow()
                .withSchedule(simpleSchedule()
                .withIntervalInSeconds(40)
                .repeatForever())
                .build()
    }
}
