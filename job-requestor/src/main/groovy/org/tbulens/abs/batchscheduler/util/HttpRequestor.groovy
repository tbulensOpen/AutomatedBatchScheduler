package org.tbulens.abs.batchscheduler.util
import com.thoughtworks.xstream.XStream
import groovyx.net.http.HTTPBuilder
import org.springframework.stereotype.Component

import static groovyx.net.http.ContentType.URLENC

@Component
class HttpRequestor {

    int send(String jobName, Map<String, Object> data) {
       //todo: Get from env.properties
        String requestUrl = "http://localhost:8080"
        HTTPBuilder http = new HTTPBuilder(requestUrl)

        String xmlData = new XStream().toXML(data)

        http.post( path: "/$jobName", body: xmlData,
                   requestContentType: URLENC ) { resp ->

         resp.statusLine.statusCode
        }
    }
}
