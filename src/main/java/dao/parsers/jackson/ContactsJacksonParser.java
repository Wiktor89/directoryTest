package dao.parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dao.DomSaxContactsParser;
import models.Entity;
import models.Group;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;
import views.ViewChangContact;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class ContactsJacksonParser extends Observable implements DomSaxContactsParser {

    private ViewChangContact model = null;

    public ContactsJacksonParser() {
        model = ViewChangContact.getViewChangContact();
    }

    @Override
    public void addContact(Entity entity) throws TransformerException,
            IOException, SAXException, ParserConfigurationException {
//        Contact contact = (Contact) entity;
//        Contact contactJac = new Contact();
//        contactJac.setName(contact.getFio());
//        contactJac.setPhone(contact.getPhone());
//        contactJac.setEmail(contact.getEmail());
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(new File("contacts"), contactJac);
        Set<models.Contact> contacts = null;
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


        Set<models.Contact> contacts = null;
        try {
            contacts = getContacts();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        model.update(this,contacts);
        return false;
    }

    @Override
    public boolean removeContact(String fio) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {
        boolean result = false;
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contacts = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contactsList = contacts.getContact();
        for (Contact contact : contactsList){
            if (contact.getName().equalsIgnoreCase(fio)){
                contactsList.remove(contact);
                result = true;
                Set<models.Contact> contactss = null;
                try {
                    contactss = getContacts();
                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }
                model.update(this,contacts);
            }
        }
        return result;
    }

    @Override
    public boolean appGroupContact(List<String> attContact) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {
        String fio = attContact.get(0);
        String name = attContact.get(1);
        boolean result = false;
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contacts = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contactsList = contacts.getContact();
        for (Contact contact : contactsList){
            if (contact.getName().equalsIgnoreCase(fio)){
                contact.setGroup(name);
                result = true;
                Set<models.Contact> contactss = null;
                try {
                    contactss = getContacts();
                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }
                model.update(this,contacts);
            }
        }
        return result;
    }

    @Override
    public boolean removeGroupContact(String fio) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {


        Set<models.Contact> contacts = null;
        try {
            contacts = getContacts();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        model.update(this,contacts);
        return false;
    }

    @Override
    public Set<models.Contact> getContacts() throws ParserConfigurationException,
            SAXException, IOException, XPathExpressionException {
        Set<models.Contact> contactsSet = new TreeSet<>();
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contacts = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contactsList = contacts.getContact();
        for (Contact contact : contactsList){
            models.Contact contact1 = new models.Contact();
            contact1.setFio(contact.getName());
            contact1.setPhone(contact.getPhone());
            contact1.setEmail(contact.getEmail());
            contact1.setGroup(new Group(contact.getGroup()));
            contactsSet.add(contact1);
        }
        return contactsSet;
    }

    @Override
    public boolean existContact(String name) throws ParserConfigurationException,
            IOException, SAXException, TransformerException {
        boolean result = false;
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contacts = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contactsList = contacts.getContact();
        for (Contact contact : contactsList){
            if (contact.getName().equalsIgnoreCase(name)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public models.Contact getContact(String fio) throws ParserConfigurationException,
            IOException, SAXException {
        models.Contact contact1 = null;
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contacts = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contactsList = contacts.getContact();
        for (Contact contact : contactsList){
            if (contact.getName().equalsIgnoreCase(fio)){
                contact1.setFio(contact.getName());
                contact1.setPhone(contact.getPhone());
                contact1.setEmail(contact.getEmail());
                contact1.setGroup(new Group(contact.getGroup()));
            }
        }
        return contact1;
    }

    @Override
    public String searchName(String fio) throws ParserConfigurationException,
            IOException, SAXException {
        String name = null;
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contacts = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contactsList = contacts.getContact();
        for (Contact contact : contactsList){
            if (contact.getName().equalsIgnoreCase(fio)){
                name = contact.getName();
            }
        }
        return name;
    }


    public static void main(String[] args) throws IOException {

//        Contact contact = (Contact) entity;
        Contact contact = new Contact();
//        contact.setName(contact.getFio());
//        contact.setPhone(contact.getPhone());
//        contact.setEmail(contact.getEmail());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("contacts"), contact);



    }
}
