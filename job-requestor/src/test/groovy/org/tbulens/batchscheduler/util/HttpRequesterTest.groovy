package org.tbulens.batchscheduler.util

import org.junit.Before
import org.junit.Test


class HttpRequesterTest {

    HttpRequester httpRequester

    @Before
    void setUp() {
        httpRequester = new HttpRequester(url: "http://google.com")
    }

    @Test
    void testSend() {
        int responseStatus = getHttpRequester().send("JobName", [:])
        assert responseStatus == 302
    }
}