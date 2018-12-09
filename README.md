
Catalina JFR
============

A Catalina valve that generates [Flight Recorder](https://openjdk.java.net/jeps/328) events.

This project requires Java 11.

![Flight Recording of some HTTP requests]()


Usage
-----

Copy the jar to ${tomcat.home}/lib and add the following valve to `server.xml`.

```xml
        <Valve className="com.github.marschall.catalina.jfr.JfrValve" />
```
