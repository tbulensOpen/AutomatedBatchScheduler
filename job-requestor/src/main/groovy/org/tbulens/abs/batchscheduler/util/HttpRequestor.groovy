package org.tbulens.abs.batchscheduler.util

import groovyx.net.http.HTTPBuilder
import org.springframework.stereotype.Component

import static groovyx.net.http.ContentType.URLENC

@Component
class HttpRequestor {

    int send(String jobName, Map<String, Object> data) {
       //todo: Get from env.properties
        String requestUrl = "http://localhost:8080"
        HTTPBuilder http = new HTTPBuilder(requestUrl)

        http.post( path: "/$jobName", body: data,
                   requestContentType: URLENC ) { resp ->

         resp.statusLine.statusCode
        }
    }
}
