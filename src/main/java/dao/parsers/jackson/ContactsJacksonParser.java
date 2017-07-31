package dao.parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dao.DomSaxContactsParser;
import models.Contact;
import models.Entity;
import models.Group;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class ContactsJacksonParser implements DomSaxContactsParser {


    @Override
    public void addContact(Entity entity) throws TransformerException,
            IOException, SAXException, ParserConfigurationException {

    }

    @Override
    public boolean updateContact(List<String> attContact) throws ParserConfigurationException,
            IOException, SAXException, TransformerException {
        return false;
    }

    @Override
    public boolean removeContact(String fio) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {
        boolean result = false;
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contacts = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<ContactJac> contactsList = contacts.getContact();
        for (ContactJac contact : contactsList){
            if (contact.getTitle().equalsIgnoreCase(fio)){
                contactsList.remove(contact);
                result = true;
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
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<ContactJac> contactsList = contacts.getContact();
        for (ContactJac contact : contactsList){
            if (contact.getTitle().equalsIgnoreCase(fio)){
                contact.setGroup(name);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeGroupContact(String fio) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        return false;
    }

    @Override
    public Set<Contact> getContacts() throws ParserConfigurationException,
            SAXException, IOException, XPathExpressionException {
        Set<Contact> contactsSet = new TreeSet<>();
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contacts = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<ContactJac> contactsList = contacts.getContact();
        for (ContactJac contact : contactsList){
            Contact contact1 = new Contact();
            contact1.setFio(contact.getTitle());
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
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<ContactJac> contactsList = contacts.getContact();
        for (ContactJac contact : contactsList){
            if (contact.getTitle().equalsIgnoreCase(name)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public Contact getContact(String fio) throws ParserConfigurationException,
            IOException, SAXException {
        Contact contact1 = null;
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contacts = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<ContactJac> contactsList = contacts.getContact();
        for (ContactJac contact : contactsList){
            if (contact.getTitle().equalsIgnoreCase(fio)){
                contact1.setFio(contact.getTitle());
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
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<ContactJac> contactsList = contacts.getContact();
        for (ContactJac contact : contactsList){
            if (contact.getTitle().equalsIgnoreCase(fio)){
                name = contact.getTitle();
            }
        }
        return name;
    }
}
