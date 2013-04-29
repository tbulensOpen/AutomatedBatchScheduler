package org.tbulens.abs.batchscheduler.util
import groovyx.net.http.HTTPBuilder

class HttpRequester {

    String muleUrl

    int send(String jobName, Map<String, Object> data) {
        HTTPBuilder http = new HTTPBuilder(muleUrl)

        http.post( path: "/$jobName", body: data,
                   requestContentType: URLENC ) { resp ->

          println "Tweet response status: ${resp.statusLine}"
          resp.statusLine.statusCode
        }
    }
}
