package dao.parsers.dom;

import dao.DomSaxGroupParser;
import models.Contact;
import models.Entity;
import models.Group;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sorted.GroupNameComparator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
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

    private Set<Group> groups = new TreeSet<>(new GroupNameComparator());

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
    public boolean removeGroup(String name) {
        return false;
    }

    @Override
    public boolean updateGroup(List<String> attGoup) {
        return false;
    }

    @Override
    public boolean existGroup(String name) {
        return false;
    }

    @Override
    public Set<String> getGroups() throws ParserConfigurationException, IOException
            ,SAXException, XPathExpressionException{
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
    public Set<Contact> getContactsGroup(String name) {
        return null;
    }
}
