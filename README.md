
Catalina JFR
============

A Catalina [valve](https://tomcat.apache.org/tomcat-10.1-doc/config/valve.html) that generates [Flight Recorder](https://openjdk.java.net/jeps/328) events. Unlike a filter based approach a valve based approach also generate events for internal servlets like the `DefaultServlet` and `JasperServlet`.


![Flight Recording of some HTTP requests](https://github.com/marschall/catalina-jfr/raw/master/src/main/javadoc/Screenshot.png)

This project requires Java 11; and Tomcat 10+.

Usage
-----

Copy the jar to `${tomcat.home}/lib` and add the following valve to `server.xml`.

```xml
        <Valve className="com.github.marschall.catalina.jfr.JfrValve" />
```

Dependency
----------

```xml
<dependency>
  <groupId>com.github.marschall</groupId>
  <artifactId>catalina-jfr</artifactId>
  <version>0.2.0</version>
</dependency>
```

Or download from [Maven Cnetral](https://central.sonatype.com/artifact/com.github.marschall/catalina-jfr/overview).

Version 0.1.0 is for Tomcat 9 and Java EE.

Correlating Dispatches
----------------------

A single request may traverse the servlet chain multiple times, either because of a server side redirect or because of asynchronous processing. We generate a unique exchangeId for every request so that multiple dispatches of the same request can be correlated.
