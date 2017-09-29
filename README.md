# Hystrix-BaseCommands

Base Commands to be extended for your use for HTTP, REST and SOAP over Hystrix

![Build Status](https://jenkins.capraconsulting.no/buildStatus/icon?job=Cantara-Hystrix-BaseCommands) - [![Project Status: Active – The project has reached a stable, usable state and is being actively developed.](http://www.repostatus.org/badges/latest/active.svg)](http://www.repostatus.org/#active) 

[![Known Vulnerabilities](https://snyk.io/test/github/Cantara/Hystrix-BaseCommands/badge.svg)](https://snyk.io/test/github/Cantara/Hystrix-BaseCommands)


## Example code

```java

/**
*domainexample
*/
public class CommandGetOauth2ProtectedPing extends BaseHttpGetHystrixCommand<String> {

    public CommandGetOauth2ProtectedPing(String uri) {

        super(URI.create(uri), "oauth-ping-group");
    }


    @Override
    protected HttpRequest dealWithRequestBeforeSend(HttpRequest request) {
        return request.authorization("Bearer " + OAUth2Value.getMyOauth2Token());
    }

    @Override
    protected String getTargetPath() {
        return "/ping";
    }
}

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
