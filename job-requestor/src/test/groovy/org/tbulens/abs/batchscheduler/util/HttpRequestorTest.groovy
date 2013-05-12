package org.tbulens.abs.batchscheduler.util

import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class HttpRequestorTest {

    HttpRequestor httpRequestor

    @Before
    void setUp() {
        // mule Instance.
//      httpRequestor = new HttpRequester(url: "http://localhost:8888")
        httpRequestor = new HttpRequestor(url: "http://google.com")
    }

    @Ignore
    @Test
    void testSend() {
        int responseStatus = getHttpRequestor().send("JobName", [:])
        assert responseStatus == 301 || 200
    }
}