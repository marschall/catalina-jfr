
Catalina JFR
============

A Catalina [valve](https://tomcat.apache.org/tomcat-9.0-doc/config/valve.html) that generates [Flight Recorder](https://openjdk.java.net/jeps/328) events. Unlike a filter based approach a valve based approach also generate events for internal servlets like the `DefaultServlet` and `JasperServlet`. This valve can correlate multiple async events that belong to the same original HTTP request.


![Flight Recording of some HTTP requests](https://github.com/marschall/catalina-jfr/raw/master/src/main/javadoc/Screenshot.png)

This project requires Java 11.

Usage
-----

Copy the jar to `${tomcat.home}/lib` and add the following valve to `server.xml`.

```xml
        <Valve className="com.github.marschall.catalina.jfr.JfrValve" />
```
