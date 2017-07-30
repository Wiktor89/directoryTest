package dao.parsers.dom;

import dao.DomSaxContactsParser;
import models.Contact;
import models.Entity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
public class DomContactParserImp implements DomSaxContactsParser {

    @Override
    public boolean addContact(Entity entity) {
        return false;
    }

    @Override
    public boolean updateContact(List<String> attContact) throws ParserConfigurationException,
            IOException, SAXException {

//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document xmlDocument  = builder.parse(new File("groups.xml"));
//
//        NodeList nodeList = xmlDocument.getElementsByTagName("group");
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            NodeList nodeChildList = nodeList.item(i).getChildNodes();
//            for (int j = 0; j < nodeChildList.getLength(); j++) {
//                Node node = nodeChildList.item(j);
//                if (node.getNodeName().equalsIgnoreCase("title")
//                        && node.getTextContent().equalsIgnoreCase(attGroup.get(0))){
//                    node.setTextContent(attGroup.get(1));
//                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
//                    Transformer transformer = transformerFactory.newTransformer();
//                    DOMSource domSource = new DOMSource(xmlDocument);
//                    StreamResult streamResult = new StreamResult(new File("groups.xml"));
//                    transformer.transform(domSource, streamResult);
//                }
//            }
//        }
        return false;
    }

    @Override
    public boolean removeContact(String fio) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {
        boolean result = false;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("contacts.xml"));

        Node root = xmlDocument.getFirstChild();

        NodeList nodeList = xmlDocument.getElementsByTagName("contact");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNodes = nodeList.item(i);
            NodeList childNodes1 = childNodes.getChildNodes();
            for (int j = 1; j < childNodes1.getLength(); j++) {
                Node item = childNodes1.item(j);
                if (item instanceof Element) {
                    if (item.getTextContent().equalsIgnoreCase(fio)) {
                        Node parentNode = item.getParentNode();
                        root.removeChild(parentNode);
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource domSource = new DOMSource(xmlDocument);
                        StreamResult streamResult = new StreamResult(new File("contacts.xml"));
                        transformer.transform(domSource, streamResult);
                        result = true;
                    }
                }
            }
        }
        return result;
    }//yes

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
        Set<Contact> contacts = new TreeSet<>();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = builder.parse(new File("contacts.xml"));
        String fio = "/contacts/contact/name";
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(fio)
                .evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            contacts.add(new Contact(nodeList.item(i).getFirstChild().getNodeValue()));
        }
        return contacts;
    }//yes

    @Override
    public boolean existContact(String name) {
        boolean result = false;
//
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document xmlDocument = builder.parse(new File("contacts.xml"));
//
//        Node root = xmlDocument.getFirstChild();
//
//        NodeList nodeList = xmlDocument.getElementsByTagName("contact");
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node childNodes = nodeList.item(i);
//            NodeList childNodes1 = childNodes.getChildNodes();
//            for (int j = 1; j < childNodes1.getLength(); j++) {
//                Node item = childNodes1.item(j);
//                if (item instanceof Element) {
//                    if (item.getTextContent().equalsIgnoreCase(name)) {
//                        Node parentNode = item.getParentNode();
//                        root.removeChild(parentNode);
//                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//                        Transformer transformer = transformerFactory.newTransformer();
//                        DOMSource domSource = new DOMSource(xmlDocument);
//                        StreamResult streamResult = new StreamResult(new File("contacts.xml"));
//                        transformer.transform(domSource, streamResult);
//                        result = true;
//                    }
//                }
//            }
//        }


        return result;
    }

    @Override
    public Contact getContact(String fio) {
        return null;
    }

    @Override
    public Contact searchName(String fio) {
        return null;
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException,
            SAXException, TransformerException {




//            NodeList childNodes = nodeList.item(i).getChildNodes();
//            for (int j = 1; j < childNodes.getLength(); j++) {
//                Node item = childNodes.item(j);
//                if (item.getTextContent().equalsIgnoreCase("test")){
//                    Node parentNode = item.getParentNode();
//                    root.removeChild(parentNode);
//                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
//                    Transformer transformer = transformerFactory.newTransformer();
//                    DOMSource domSource = new DOMSource(xmlDocument);
//                    StreamResult streamResult = new StreamResult(new File("contacts.xml"));
//                    transformer.transform(domSource, streamResult);
//                }
//            }
//        }
    }

}
