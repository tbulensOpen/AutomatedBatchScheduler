package org.tbulens.abs.batchscheduler.quartz

import org.quartz.Scheduler
import org.quartz.impl.StdSchedulerFactory

class QuartzFactory {


    Scheduler createScheduler() {
        StdSchedulerFactory.getDefaultScheduler()
    }
}
