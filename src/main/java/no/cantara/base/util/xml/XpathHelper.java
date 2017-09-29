package no.cantara.base.util.xml;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

import static no.cantara.base.util.StringHelper.hasContent;

public class XpathHelper {

    private static final Logger log = LoggerFactory.getLogger(XpathHelper.class);


    public static String findValue(String xmlString, String expression) {
        String value = "";
        if (hasContent(xmlString) && hasContent(expression)) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(new StringReader(xmlString)));
                XPath xPath = XPathFactory.newInstance().newXPath();


                XPathExpression xPathExpression = xPath.compile(expression);
                value = xPathExpression.evaluate(doc);
            } catch (Exception e) {
                log.warn("Failed to parse xml. Expression {}, xml {}, ", expression, xmlString, e);
            }
        }
        return value;
    }

    public static String findXmlTree(String xml, String nodeName) {
        String tree = null;
        if (hasContent(xml) && hasContent(nodeName)){
            int start = xml.indexOf("<" + nodeName);
            if (start > 0) {
                int last = xml.indexOf("</" + nodeName, start);
                if (last >0) {
                    int end = last + nodeName.length() + 3;
                    tree = xml.substring(start,end );
                }
            }
        }
        return tree;
    }

}
