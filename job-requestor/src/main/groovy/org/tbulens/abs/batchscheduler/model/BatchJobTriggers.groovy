package org.tbulens.abs.batchscheduler.model

import org.quartz.Trigger


class BatchJobTriggers {
    Map<String, Trigger> cronTriggers = [:]
    Map<String, Trigger> simpleTriggers = [:]

    Trigger get(String triggerName) {
        cronTriggers.get(triggerName)
    }

    void addCronTrigger(String triggerName, Trigger trigger) {
       cronTriggers.put(triggerName, trigger)
    }

    void addSimpleTrigger(String jobName, Trigger trigger) {
        simpleTriggers.put(jobName, trigger)
    }
}
