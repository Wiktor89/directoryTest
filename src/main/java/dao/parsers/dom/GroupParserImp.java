package dao.parsers.dom;

import dao.DomSaxGroupParser;
import models.Contact;
import models.Entity;
import models.Group;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class GroupParserImp implements DomSaxGroupParser {

    @Override
    public boolean addGroup(Entity entity) throws ParserConfigurationException, TransformerException {
        File file = new File("refbook.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.newDocument();

        Element element = document.createElement("group");
        document.appendChild(element);

        Attr attr = document.createAttribute("id");
        attr.setValue("2");
        element.setAttributeNode(attr);

        Element name = document.createElement("name");
        name.appendChild(document.createTextNode("test1"));
        element.appendChild(name);

        TransformerFactory factory1 = TransformerFactory.newInstance();
        Transformer transformer = factory1.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);
        transformer.transform(source,result);
        return false;

    }

    @Override
    public boolean remGroup(String name) {
        return false;
    }

    @Override
    public boolean updGroup(List<String> attGoup) {
        return false;
    }

    @Override
    public boolean existGroup(String name) {
        return false;
    }

    @Override
    public Set<Group> getGroups() {


        return null;
    }

    @Override
    public Set<Contact> getContactsGroup(String name) {
        return null;
    }
}
