package org.tbulens.abs.testjob.service

import org.junit.Before
import org.junit.Test


class HelloWorldImplTest {

    HelloWorld helloWorld

    @Before
    void setUp() {
        helloWorld = new HelloWorldImpl()
    }


    @Test
    void sayHello() {
         helloWorld.sayHello()
    }
}
