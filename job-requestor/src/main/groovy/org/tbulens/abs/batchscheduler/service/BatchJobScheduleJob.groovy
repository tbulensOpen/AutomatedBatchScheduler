package org.tbulens.abs.batchscheduler.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

class BatchJobScheduleJob {

    static ApplicationContext context
    @Autowired BatchJobScheduleService batchJobScheduleService

      static void main(String[] args) {
            load()
            BatchJobScheduleService batchJobScheduleService = context.getBean("batchJobScheduleService", BatchJobScheduleService)
            batchJobScheduleService.load()
      }

      static synchronized ApplicationContext load() {
              if (!context) {
                  context = new ClassPathXmlApplicationContext("classpath:contexts/batchJobScheduleContext.xml")
              }
              context
      }


      private static void printInstructions() {
          println "***********************************************************"
          println "* Welcome to Automated Batch Job Scheduler Program."
          println "***********************************************************"
      }

      private static void printErrorInstruction(String path) {
          println "***********************************************************"
          println "* Welcome to Automated Batch Job Scheduler Program."
          println "***********************************************************"

      }

}
