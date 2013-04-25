package org.tbulens.abs.batchscheduler.quartz

import org.quartz.Trigger
import org.tbulens.abs.batchscheduler.model.BatchJobCronTriggers
import org.tbulens.abs.domain.model.BatchJob

class BatchJobTriggersFactory {

    QuartzFactory quartzFactory

    BatchJobCronTriggers create(List<BatchJob> batchJobs) {
        BatchJobCronTriggers batchJobTriggers = new BatchJobCronTriggers()

        Trigger trigger

         batchJobs.each { batchJob ->
             if (batchJobs.cronExpression) {
                 trigger = createTrigger(batchJob.cronExpression.name, batchJob.cronExpression.expression)
                 batchJobTriggers.addCronTrigger(batchJob.cronExpression.name, trigger)
             }
             else {
                 trigger = createTrigger(batchJob.jobName, batchJob.groupName)
                 batchJobTriggers.addSimpleTrigger(batchJob.jobName, trigger)
             }

         }
        batchJobTriggers
    }

    private Trigger createTrigger(String name, String groupName) {
        quartzFactory.createSimpleTrigger(name, groupName)
    }
}
