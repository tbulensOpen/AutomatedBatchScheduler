package org.tbulens.abs.batchscheduler.quartz

import org.quartz.Trigger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.tbulens.abs.batchscheduler.model.BatchJobTriggers
import org.tbulens.abs.domain.model.BatchJob

@Component
class BatchJobTriggersFactory {

    @Autowired QuartzFactory quartzFactory

    BatchJobTriggers create(List<BatchJob> batchJobs) {
        BatchJobTriggers batchJobTriggers = new BatchJobTriggers()

        Trigger trigger

         batchJobs.each { batchJob ->
             if (batchJob.cronExpression) {
                 trigger = quartzFactory.createCronTriggerNew(batchJob.cronExpression.name, batchJob.cronExpression.expression)
                 batchJobTriggers.addCronTrigger(batchJob, trigger)
             }
             else {
                 trigger = quartzFactory.createSimpleTrigger(batchJob.jobName, batchJob.groupName)
                 batchJobTriggers.addSimpleTrigger(batchJob, trigger)
             }

         }
        batchJobTriggers
    }
}
