package org.tbulens.batchscheduler.util

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.URLENC

class HttpRequester {

    String url

    int send(String jobName, Map<String, String> data) {
        HTTPBuilder http = new HTTPBuilder(url)

        http.post( path: "/$jobName", body: data,
                   requestContentType: URLENC ) { resp ->

          println "Tweet response status: ${resp.statusLine}"
          resp.statusLine.statusCode
        }
    }
}
