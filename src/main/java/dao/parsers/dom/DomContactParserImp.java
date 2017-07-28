package dao.parsers.dom;

import dao.DomSaxContactsParser;
import models.Contact;
import models.Entity;
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
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class DomContactParserImp extends Parser implements DomSaxContactsParser {

    @Override
    public boolean addContact(Entity entity) {
        return false;
    }

    @Override
    public boolean updateContact(List<String> attContact) {
        return false;
    }

    @Override
    public boolean removeContact(String fio) {
        return false;
    }

    @Override
    public boolean appGroupContact(List<String> attContact) {
        return false;
    }

    @Override
    public boolean removeGroupContact(String fio) {
        return false;
    }

    @Override
    public Set<Contact> getContacts() throws ParserConfigurationException
            , SAXException, IOException, XPathExpressionException {
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

    @Override
    public boolean existContact(String name) {
        return false;
    }

    @Override
    public Contact getContact(String fio) {
        return null;
    }

    @Override
    public Contact searchName(String fio) {
        return null;
    }
}
