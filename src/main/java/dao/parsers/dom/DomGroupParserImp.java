package dao.parsers.dom;

import dao.DomSaxGroupParser;
import models.Entity;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

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
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class DomGroupParserImp implements DomSaxGroupParser {


    @Override
    public boolean addGroup(Entity entity) throws ParserConfigurationException, TransformerException {
        File file = new File("refbook.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element groupEl = document.createElement("group");
        Attr id = document.createAttribute("id");
        id.setValue("1");
        groupEl.setAttributeNode(id);
        document.appendChild(groupEl);


        Element titleEl = document.createElement("title");
        titleEl.appendChild(document.createTextNode("test"));
        groupEl.appendChild(titleEl);





        return false;

    }

    @Override
    public boolean removeGroup(String name) throws ParserConfigurationException,
            SAXException, XPathExpressionException, TransformerException {



        return false;
    }

    @Override
    public void updateGroup(List<String> attGroup) throws ParserConfigurationException,
            SAXException, TransformerException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = builder.parse(new File("groups.xml"));

        Node root = xmlDocument.getFirstChild();

        Node group = xmlDocument.getElementsByTagName("group").item(0);

        NamedNodeMap namedNodeMap = group.getAttributes();

        Node nodeAttr = namedNodeMap.getNamedItem("id");

        nodeAttr.setTextContent("11111111111");

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(xmlDocument);
        StreamResult streamResult = new StreamResult(new File("groups.xml"));
        transformer.transform(domSource, streamResult);
    }

    @Override
    public boolean existGroup(String name) {
        return false;
    }

    @Override
    public Set<String> getGroups() throws ParserConfigurationException
            , SAXException, XPathExpressionException, IOException {
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
//        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.setOutputProperty(OutputKeys.INDENT,"yes");//для переносов
//        transformer.transform(new DOMSource());

//        String s = document.getDocumentElement().getNodeName();
//        System.out.println(s);
//        NodeList nodeList = document.getElementsByTagName("group");
//        Element element1 = (Element) nodeList.item(0);
//        String id = element1.getElementsByTagName("id").item(0).getNodeValue();
//        System.out.println(id);
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Element element = (Element) nodeList.item(i);
//            String title = element.getElementsByTagName("title").item(0).getChildNodes()
//                    .item(0).getNodeValue();
//            String nodeValue = element.getParentNode().getAttributes()
//                    .getNamedItem("id").getNodeName();
//            System.out.println(id);
//            groups.add(new Group(title));
//        }

    }

    @Override
    public Set<String> getContactsGroup(String name) throws ParserConfigurationException,
            SAXException, XPathExpressionException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = builder.parse(new File("refbook.xml"));

        Set<String> contacts = new TreeSet<>();
        String titls = "/entity/contact";
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
