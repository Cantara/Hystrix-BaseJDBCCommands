# Hystrix-BaseJDBCCommands

Base Commands to be extended for your use for HTTP, REST and SOAP over Hystrix

![Build Status](https://jenkins.quadim.ai/buildStatus/icon?job=Hystrix-BaseJDBCCommands) - [![Project Status: Active – The project has reached a stable, usable state and is being actively developed.](http://www.repostatus.org/badges/latest/active.svg)](http://www.repostatus.org/#active) - [![Known Vulnerabilities](https://snyk.io/test/github/Cantara/Hystrix-BaseCommands/badge.svg)](https://snyk.io/test/github/Cantara/Hystrix-BaseCommands)


## Example code

```java

/**
*domainexample
*/


//
//
// Use of the CommandGetOauth2ProtectedPing command above
//
log.trace("Calling {}", testServer.getUrl());
String returned_data = new CommandGetOauth2ProtectedPing(testServer.getUrl()).execute();
log.debug("Returned: " + returned_data);

```


## Binaries

Binaries and dependency information for Maven, Ivy, Gradle and others can be found at [http://mvnrepo.cantara.no](http://mvnrepo.cantara.no/index.html#nexus-search;classname~Whydah).

Example for Maven:

```xml
<dependency>
      <groupId>no.cantara.base</groupId>
      <artifactId>Hystrix-BaseCommands</artifactId>
      <version>0.1.2</version>
</dependency>
```
