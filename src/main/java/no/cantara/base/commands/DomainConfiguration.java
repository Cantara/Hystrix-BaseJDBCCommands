package no.cantara.base.commands;


import no.cantara.base.util.Property;

import java.net.URI;

public interface DomainConfiguration {
    @Property(key = "domainexample.uri")
    URI getDomainExampleServerUri();

    @Property(key = "domainexample.username")
    String getUsername();

    @Property(key = "domainexample.password")
    String getPassword();

}
