package org.tbulens.abs.batchscheduler.quartz
import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.impl.StdSchedulerFactory
import org.tbulens.abs.batchscheduler.service.JobRequester
import org.tbulens.abs.domain.model.BatchJob
import org.tbulens.abs.domain.model.CronExpression

import static org.quartz.CronScheduleBuilder.cronSchedule
import static org.quartz.JobBuilder.newJob
import static org.quartz.SimpleScheduleBuilder.simpleSchedule
import static org.quartz.TriggerBuilder.newTrigger

class QuartzFactory {


    Scheduler createSchedule() {
        StdSchedulerFactory.getDefaultScheduler()
    }

    Trigger createCronTriggerNew(String triggerName, String cronExpression) {
          Trigger trigger = newTrigger()
                  .withIdentity(triggerName)
                  .withSchedule(cronSchedule(cronExpression))
                  .build()
      }

    Trigger createSimpleTrigger(String jobName, String jobGroup) {
          Trigger trigger = newTrigger()
                  .withIdentity(jobName, jobGroup)
                  .startNow()
                  .withSchedule(simpleSchedule())
                  .build()
      }

    void scheduleJob(Scheduler scheduler, BatchJob batchJob) {
        JobDetail job = createJob(batchJob.jobName, batchJob.groupName)

        Trigger trigger = null
        CronExpression cronExpression = batchJob.cronExpression

        if (cronExpression) {
            trigger = createCronTrigger(batchJob)
        } else {
            trigger = createSimpleTrigger(batchJob.jobName, batchJob.groupName)
        }

        scheduler.scheduleJob(job, trigger);
    }

    private JobDetail createJob(String jobName, String jobGroup) {
        newJob(JobRequester.class)
                .withIdentity(jobName, jobGroup)
                .build()
    }



    private Trigger createCronTrigger(BatchJob batchJob) {
        CronExpression cronExpression = batchJob.cronExpression
        Trigger trigger = newTrigger()
                .withIdentity(cronExpression.name, batchJob.groupName)
                .withSchedule(cronSchedule(cronExpression.expression))
                .forJob(batchJob.jobName, batchJob.groupName)
                .build();
    }



}
