package org.tbulens.abs.batchscheduler.util

import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class HttpRequesterTest {

    HttpRequester httpRequester

    @Before
    void setUp() {
        // mule Instance.
//      httpRequester = new HttpRequester(url: "http://localhost:8888")
        httpRequester = new HttpRequester(url: "http://google.com")
    }

    @Ignore
    @Test
    void testSend() {
        int responseStatus = getHttpRequester().send("JobName", [:])
        assert responseStatus == 301 || 200
    }
}