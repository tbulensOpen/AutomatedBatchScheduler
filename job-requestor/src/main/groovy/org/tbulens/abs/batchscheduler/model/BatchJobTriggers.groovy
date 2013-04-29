package org.tbulens.abs.batchscheduler.model

import org.quartz.Trigger
import org.tbulens.abs.domain.model.BatchJob


class BatchJobTriggers {
    Map<String, Trigger> cronTriggers = [:]
    Map<String, Trigger> simpleTriggers = [:]
    List<BatchJob> cronJobs = []
    List<BatchJob> simpleJobs = []

    Trigger getCronTrigger(String triggerName) {
        cronTriggers.get(triggerName)
    }

    Trigger getSimpleTrigger(String jobName) {
        simpleTriggers.get(jobName)
    }

    void addCronTrigger(BatchJob batchJob, Trigger trigger) {
       cronTriggers.put(batchJob.cronExpression.name, trigger)
        cronJobs.add(batchJob)
    }

    void addSimpleTrigger(BatchJob batchJob, Trigger trigger) {
        simpleTriggers.put(batchJob.jobName, trigger)
        simpleJobs.add(batchJob)
    }
}
