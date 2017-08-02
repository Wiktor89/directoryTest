package parsers.dom;

import dao.DomSaxContacts;
import models.Contact;
import models.Entity;
import models.Group;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import views.ViewChangContact;

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
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.TreeSet;

/**
 *Dom парсер для контакта
 */
public class DomContactImp extends Observable implements DomSaxContacts {

    private ViewChangContact model = null;

    public DomContactImp() {
        model = ViewChangContact.getViewChangContact();
    }

    @Override
    public void addContact(Entity entity) throws TransformerException, IOException,
            SAXException, ParserConfigurationException {

        Contact contact = (Contact) entity;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument  = null;
        Element rootContacts = null;

        File file = new File("contacts.xml");
        if (file.exists()){
            xmlDocument = builder.parse(file);
            rootContacts = xmlDocument.getDocumentElement();

        }else {
            xmlDocument = builder.newDocument();
            rootContacts = xmlDocument.createElement("contacts");
            xmlDocument.appendChild(rootContacts);
        }

        Element contactEl = xmlDocument.createElement("contact");
        Element nameEl = xmlDocument.createElement("name");
        contactEl.appendChild(nameEl);
        Element phoneEl = xmlDocument.createElement("phone");
        contactEl.appendChild(phoneEl);
        Element emailEl = xmlDocument.createElement("email");
        contactEl.appendChild(emailEl);
        Element groupEl = xmlDocument.createElement("group");
        contactEl.appendChild(groupEl);

        Attr id = xmlDocument.createAttribute("id");
        id.setValue(String.valueOf(contact.getId()));
        contactEl.setAttributeNode(id);

        Text nameText = xmlDocument.createTextNode(contact.getFio());
        nameEl.appendChild(nameText);

        Text phoneText = xmlDocument.createTextNode(contact.getPhone());
        phoneEl.appendChild(phoneText);

        Text emailText = xmlDocument.createTextNode(contact.getEmail());
        emailEl.appendChild(emailText);

        Text groupText = xmlDocument.createTextNode("нет группы");
        groupEl.appendChild(groupText);

        rootContacts.appendChild(contactEl);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(new DOMSource(xmlDocument),new StreamResult(
                new FileOutputStream(file)));
        Set<Contact> contacts = null;
        try {
            contacts = getContacts();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        model.update(this,contacts);

    }

    @Override
    public boolean updateContact(List<String> attContact) throws ParserConfigurationException,
            IOException, SAXException, TransformerException {
        boolean result = false;
        String oldName = attContact.get(0);
        String newName = attContact.get(1);
        String newPhone = attContact.get(2);
        String newEmail = attContact.get(3);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("contacts.xml"));

        NodeList nodeList = xmlDocument.getElementsByTagName("contact");
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList nodeChildList = nodeList.item(i).getChildNodes();
            for (int j = 0; j < nodeChildList.getLength(); j++) {
                Node node = nodeChildList.item(j);
                if (node instanceof Element && node.getTextContent().equalsIgnoreCase(oldName)) {
                    for (int k = 0; k < nodeChildList.getLength(); k++) {
                        Node childPhone = nodeChildList.item(k);
                        switch (childPhone.getNodeName()) {
                            case "name": {
                                childPhone.setTextContent(newName);
                                break;
                            }
                            case "phone": {
                                childPhone.setTextContent(newPhone);
                                break;
                            }
                            case "email": {
                                childPhone.setTextContent(newEmail);
                                break;
                            }
                            default:
                                break;
                        }
                    }
                    result = true;
                }
            }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(xmlDocument);
            StreamResult streamResult = new StreamResult(new File("contacts.xml"));
            transformer.transform(domSource, streamResult);
            Set<Contact> contacts = null;
            try {
                contacts = getContacts();
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
            model.update(this,contacts);

        return result;
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
        Set<Contact> contacts = null;
        try {
            contacts = getContacts();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        model.update(this,contacts);
        return result;
    }

    @Override
    public boolean appGroupContact(List<String> attContact) throws IOException,
            SAXException, ParserConfigurationException, TransformerException {
        boolean result = false;
        String fio = attContact.get(0);
        String name = attContact.get(1);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("contacts.xml"));

        NodeList contact = document.getElementsByTagName("contact");
        for (int i = 0; i < contact.getLength(); i++) {
            NodeList childNodes = contact.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node item = childNodes.item(j);
                if (item instanceof Element && item.getTextContent().equalsIgnoreCase(fio)){
                    childNodes.item(7).setTextContent(name);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(document);
                    StreamResult streamResult = new StreamResult(new File("contacts.xml"));
                    transformer.transform(domSource, streamResult);
                    result = true;
                }
            }
        }
        Set<Contact> contacts = null;
        try {
            contacts = getContacts();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        model.update(this,contacts);

        return result;
    }

    @Override
    public boolean removeGroupContact(String fio) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {
        boolean result = false;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("contacts.xml"));

        NodeList contact = document.getElementsByTagName("contact");
        for (int i = 0; i < contact.getLength(); i++) {
            NodeList childNodes = contact.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node item = childNodes.item(j);
                if (item instanceof Element && item.getTextContent().equalsIgnoreCase(fio)){
                    Node item1 = childNodes.item(7);
                    item1.setTextContent("нет группы");
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource domSource = new DOMSource(document);
                    StreamResult streamResult = new StreamResult(new File("contacts.xml"));
                    transformer.transform(domSource, streamResult);
                    result = true;
                }
            }
        }
        Set<Contact> contacts = null;
        try {
            contacts = getContacts();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        model.update(this,contacts);

        return result;
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
    }

    @Override
    public boolean existContact(String name) throws ParserConfigurationException,
            IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("contacts.xml"));

        NodeList contact = document.getElementsByTagName("contact");
        for (int i = 0; i < contact.getLength(); i++) {
            NodeList childNodes = contact.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node item = childNodes.item(j);
                if (item.getTextContent().equalsIgnoreCase(name)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Contact getContact(String fio) throws ParserConfigurationException,
            IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("contacts.xml"));

        NodeList contact = document.getElementsByTagName("contact");
        for (int i = 0; i < contact.getLength(); i++) {
            NodeList childNodes = contact.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node item = childNodes.item(j);
                if (item instanceof Element && item.getTextContent().equalsIgnoreCase(fio)){
                    Contact contact1 = new Contact();
                    contact1.setFio(item.getTextContent());
                    contact1.setPhone(childNodes.item(3).getTextContent());
                    contact1.setEmail(childNodes.item(5).getTextContent());
                    contact1.setGroup(new Group(childNodes.item(7).getTextContent()));
                    return contact1;
                }
            }
        }
        return null;
    }

    @Override
    public String searchName(String fio) throws ParserConfigurationException,
            IOException, SAXException {
        String contact = "";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("contacts.xml"));

        NodeList contact1 = document.getElementsByTagName("contact");
        for (int i = 0; i < contact1.getLength(); i++) {
            NodeList childNodes = contact1.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node item = childNodes.item(j);
                if (item instanceof Element && item.getTextContent().equalsIgnoreCase(fio)){
                    contact = item.getTextContent();
                }
            }
        }
        return contact;
    }

}
