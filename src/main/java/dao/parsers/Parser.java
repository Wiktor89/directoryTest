package dao.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public abstract class Parser {

    public Set<String> getContacts () throws ParserConfigurationException,
            IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Set<String> contacts = new TreeSet<>();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = builder.parse(new File("refbook.xml"));
        String fio = "/entity/contact/name";
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(fio)
                .evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            contacts.add(nodeList.item(i).getFirstChild().getNodeValue());
        }
        return contacts;
    }

    public Set<String> getGroups() throws ParserConfigurationException,
            IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Set<String> groups = new TreeSet<>();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = builder.parse(new File("refbook.xml"));
        String titls = "/entity/group/title";
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(titls)
                .evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            groups.add(nodeList.item(i).getFirstChild().getNodeValue());
        }

        return groups;
    }
}
