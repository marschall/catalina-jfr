package com.github.marschall.catalina.jfr;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.Valve;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.StackTrace;

/**
* An {@link Valve} that generates <a href="https://openjdk.java.net/jeps/328">Flight Recorder</a> events.
*/
public class JfrValve extends ValveBase {

  // http://hirt.se/blog/?p=870
  // https://www.oracle.com/technetwork/oem/soa-mgmt/con10912-javaflightrecorder-2342054.pdf

  @Override
  public void invoke(Request request, Response response) throws IOException, ServletException {
    boolean isAsync = request.isAsyncStarted();
    if (isAsync) {
      this.getNext().invoke(request, response);
    } else {
      HttpEvent event = new HttpEvent();
      event.setMethod(request.getMethod());
      event.setUri(request.getRequestURI());
      event.setQuery(request.getQueryString());
      event.begin();
      try {
        this.getNext().invoke(request, response);
      } finally {
        if (!isAsync) {
          event.end();
          event.commit();
        }
      }
    }


  }

  @Label("HTTP exchange")
  @Description("An HTTP exchange")
  @Category("HTTP")
  @StackTrace(false)
  static class HttpEvent extends Event {

    @Label("Method")
    @Description("The HTTP method")
    private String method;

    @Label("URI")
    @Description("The request URI")
    private String uri;

    @Label("Query")
    @Description("The query string")
    private String query;

    String getMethod() {
      return this.method;
    }

    void setMethod(String operationName) {
      this.method = operationName;
    }

    String getUri() {
      return this.uri;
    }

    void setUri(String query) {
      this.uri = query;
    }

    String getQuery() {
      return this.query;
    }

    void setQuery(String query) {
      this.query = query;
    }

  }

}
