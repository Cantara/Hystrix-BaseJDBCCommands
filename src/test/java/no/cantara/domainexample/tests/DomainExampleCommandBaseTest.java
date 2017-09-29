package no.cantara.domainexample.tests;

import no.cantara.base.commands.DomainConfiguration;
import no.cantara.domainexample.TestDomainExampleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

@Test(groups = { "DomainExampleCommandBaseTest" })
public class DomainExampleCommandBaseTest {
    private final static Logger LOG = LoggerFactory.getLogger(DomainExampleCommandBaseTest.class);

    protected static DomainConfiguration configuration = new TestDomainExampleConfiguration() {
    };

    @BeforeGroups(value = { "DomainExampleCommandBaseTest" })
    public void setup() {
        LOG.debug("DomainExampleCommandBaseTest setup: {}");
    }



}

