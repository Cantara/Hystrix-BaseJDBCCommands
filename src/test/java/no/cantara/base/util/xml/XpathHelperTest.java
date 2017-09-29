package no.cantara.base.util.xml;

import org.slf4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static no.cantara.base.util.xml.XpathHelper.findValue;
import static no.cantara.base.util.xml.XpathHelper.findXmlTree;
import static org.slf4j.LoggerFactory.getLogger;
import static org.testng.Assert.*;

/**
 * Created by baardl on 2017-03-29.
 */
public class XpathHelperTest {
    private static final Logger log = getLogger(XpathHelperTest.class);
    @BeforeMethod
    public void setUp() throws Exception {

    }

    @Test
    public void testFindValueOk() throws Exception {
        String value = findValue(xml, "//base");
        assertNotNull(value);
        assertEquals(value, "test");

    }

    @Test
    public void testFindValueMissingValue() throws Exception {
        assertEquals("", findValue(null, null));
        assertEquals("", findValue(null, ""));
        assertEquals("", findValue("", null));
        assertEquals("", findValue(xml, null));
        assertEquals("", findValue(null, "//base"));

    }

    @Test
    public void testSubtree() throws Exception {
        String result = findXmlTree(xml, "cantara");
        log.debug("Result {}", result);
        assertTrue(result.startsWith("<cantara"));
        assertTrue(result.endsWith("</cantara>"));

    }

    private static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<no><cantara><base>test</base></cantara></no>";

}