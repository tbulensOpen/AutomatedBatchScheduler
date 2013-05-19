package org.tbulens.abs.testjob.service

import org.apache.commons.logging.LogFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.util.Log4jConfigurer

public class HelloWorldImpl implements HelloWorld {

    @Override
    String sayHello(Map<String, Object> data) {

       ApplicationContext context =  new ClassPathXmlApplicationContext("classpath:contexts/testJob-loggingContext.xml")
       context.getBean("log4jInitialization", Log4jConfigurer)

        LogFactory.getLog(this.class).error("Welcome to HelloWorldImpl...")

        "Hello, why are we not working yet."
//        throw new RuntimeException("Data Map values = " + data.keySet().asList().join(","))
    }
}
