package dao.dom;

import dao.GroupDao;
import models.Entity;
import models.Group;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import views.ViewChangGroup;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 *Dom парсер для групп
 */
public class GroupsDaoImpl extends Observable implements GroupDao {

    private ViewChangGroup model = null;

    public GroupsDaoImpl() {
        model = ViewChangGroup.getViewChangGroup();
    }

    @Override
    public void addGroup(Entity entity) throws ParserConfigurationException,
            TransformerException, IOException, SAXException {
        Group group  =(Group) entity;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = null;
        Element rootGroups = null;

        File file = new File("groups.xml");
        if (file.exists()){
            xmlDocument = builder.parse(file);
            rootGroups = xmlDocument.getDocumentElement();

        }else {
            xmlDocument = builder.newDocument();
            rootGroups = xmlDocument.createElement("groups");
            xmlDocument.appendChild(rootGroups);
        }
        Element groupEl = xmlDocument.createElement("group");
        rootGroups.appendChild(groupEl);

        Attr id = xmlDocument.createAttribute("id");
        id.setValue(String.valueOf(group.getId()));
        groupEl.setAttributeNode(id);

        Element titleEl = xmlDocument.createElement("title");
        groupEl.appendChild(titleEl);

        Text titleText = xmlDocument.createTextNode(group.getName());
        titleEl.appendChild(titleText);
        rootGroups.appendChild(groupEl);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(new DOMSource(xmlDocument),new StreamResult(
                new FileOutputStream(file)));
        Set<String> groups = null;
        try {
            groups = getGroups();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        model.update(this,groups);
    }

    @Override
    public boolean removeGroup(String name) throws ParserConfigurationException,
            SAXException, XPathExpressionException, TransformerException, IOException {
        boolean result = false;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = builder.parse(new File("groups.xml"));

        Node root = xmlDocument.getFirstChild();
        NodeList nodeList = xmlDocument.getElementsByTagName("group");
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList childNodes = nodeList.item(i).getChildNodes();
            for (int j = 1; j < childNodes.getLength(); j++) {
                Node item = childNodes.item(j);
                if (item.getTextContent().equalsIgnoreCase(name)){
                    Node parentNode = item.getParentNode();
                    root.removeChild(parentNode);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(xmlDocument);
                    StreamResult streamResult = new StreamResult(new File("groups.xml"));
                    transformer.transform(domSource, streamResult);
                    result = true;
                    Set<String> groups = null;
                    try {
                        groups = getGroups();
                    } catch (XPathExpressionException e) {
                        e.printStackTrace();
                    }
                    model.update(this,groups);
                }
            }
        }

        return result;
    }

    @Override
    public void updateGroup(List<String> attGroup) throws ParserConfigurationException,
            SAXException, TransformerException, IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = builder.parse(new File("groups.xml"));

        NodeList nodeList = xmlDocument.getElementsByTagName("group");
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList nodeChildList = nodeList.item(i).getChildNodes();
            for (int j = 0; j < nodeChildList.getLength(); j++) {
                Node node = nodeChildList.item(j);
                if (node.getNodeName().equalsIgnoreCase("title")
                        && node.getTextContent().equalsIgnoreCase(attGroup.get(0))){
                    node.setTextContent(attGroup.get(1));
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(xmlDocument);
                    StreamResult streamResult = new StreamResult(new File("groups.xml"));
                    transformer.transform(domSource, streamResult);
                    Set<String> groups = null;
                    try {
                        groups = getGroups();
                    } catch (XPathExpressionException e) {
                        e.printStackTrace();
                    }
                    model.update(this,groups);
                }
            }
        }
    }

    @Override
    public boolean existGroup(String name) throws ParserConfigurationException,
            IOException, SAXException, XPathExpressionException {
        boolean result = false;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();

        Document xmlDocument  = null;
            xmlDocument = builder.parse(new File("groups.xml"));
        String title = "/groups/group/title";
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = null;
            nodeList = (NodeList) xPath.compile(title)
                    .evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getFirstChild().getNodeValue().equalsIgnoreCase(name)){
                result = true;
            }
        }
        return result;
    }

        @Override
    public Set<String> getGroups() throws ParserConfigurationException
            , SAXException, XPathExpressionException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Set<String> groups = new TreeSet<>();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = builder.parse(new File("groups.xml"));
        String title = "/groups/group/title";
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(title)
                .evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            groups.add(nodeList.item(i).getFirstChild().getNodeValue());
        }
        return groups;
    }

    @Override
    public Set<String> getContactsGroup(String name) throws ParserConfigurationException,
            SAXException, XPathExpressionException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = builder.parse(new File("contacts"));

        Set<String> contacts = new TreeSet<>();
        String titls = "/contacts/contact";
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(titls)
                .evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeName().equalsIgnoreCase("contact")){
                NodeList childNodeList = nodeList.item(i).getChildNodes();
                for (int j = 0; j < childNodeList.getLength(); j++) {
                    if (childNodeList.item(j).getNodeName().equalsIgnoreCase("group")){
                        if (childNodeList.item(j).getTextContent().equalsIgnoreCase(name)){
                            contacts.add(childNodeList.item(1).getTextContent());
                        }
                    }
                }
            }
        }
        return contacts;
    }

}
