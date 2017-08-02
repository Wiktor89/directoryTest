package dao.parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
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
 *Jac парсер для контакта
 */
public class ContactsJacksonParser extends Observable implements DomSaxContactsParser {

    private ViewChangContact model = null;

    public ContactsJacksonParser() {
        model = ViewChangContact.getViewChangContact();
    }

    @Override
    public void addContact(Entity entity) throws TransformerException,
            IOException, SAXException, ParserConfigurationException {
        File file = new File("contacts.xml");
        models.Contact contactModel = (models.Contact) entity;
        Contact contactJac = new Contact();
        contactJac.setName(contactModel.getFio());
        contactJac.setPhone(contactModel.getPhone());
        contactJac.setEmail(contactModel.getEmail());
        contactJac.setGroup(contactModel.getGroup().getName());

        ObjectMapper objectMapper = new XmlMapper();
        Contacts contactList = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);

        List<Contact> contacts = contactList.getContact();

        contacts.add(contactJac);

        Set<models.Contact> contacts1 = new TreeSet<>();
        for (Contact contact : contacts){
            models.Contact contactMo = new models.Contact();
            contactMo.setPhone(contact.getPhone());
            contactMo.setFio(contact.getName());
            contactMo.setEmail(contact.getEmail());
            contactMo.setGroup(new Group(contact.getGroup()));
            contacts1.add(contactMo);
        }

        XmlMapper mapper = new XmlMapper();
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
        mapper.writeValue(file,contactList);
        model.update(this,contacts1);


    }

    @Override
    public boolean updateContact(List<String> attContact) throws ParserConfigurationException,
            IOException, SAXException, TransformerException {
        boolean result = false;
        File file = new File("contacts.xml");

        ObjectMapper objectMapper = new XmlMapper();
        Contacts contactList = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);

        List<Contact> contacts = contactList.getContact();
        for (Contact contact : contacts){
            if (contact.getName().equalsIgnoreCase(attContact.get(0))){
                contact.setName(attContact.get(1));
                contact.setPhone(attContact.get(2));
                contact.setEmail(attContact.get(3));
            }
        }
        XmlMapper mapper = new XmlMapper();
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
        mapper.writeValue(file,contactList);
        Set<models.Contact> contacts1 = new TreeSet<>();
        for (Contact contact : contacts){
            models.Contact contactMo = new models.Contact();
            contactMo.setFio(contact.getName());
            contactMo.setPhone(contact.getPhone());
            contactMo.setEmail(contact.getEmail());
            contactMo.setGroup(new Group(contact.getGroup()));
            contacts1.add(contactMo);
            result = true;
        }
        model.update(this,contacts1);

        return result;
    }

    @Override
    public boolean removeContact(String fio) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {
        boolean result = false;
        File file = new File("contacts.xml");
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contactList = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contacts = contactList.getContact();
        for (Contact contact : contacts){
            if (contact.getName().equalsIgnoreCase(fio)){
                contacts.remove(contact);
                result = true;
                break;
            }
        }
        XmlMapper mapper = new XmlMapper();
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
        mapper.writeValue(file,contactList);

        Set<models.Contact> contacts1 = new TreeSet<>();
        model.update(this,contacts1);
        return result;
    }

    @Override
    public boolean appGroupContact(List<String> attContact) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {
        String fio = attContact.get(0);
        String name = attContact.get(1);
        boolean result = false;
        File file = new File("contacts.xml");
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contactList = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);

        List<Contact> contacts = contactList.getContact();
        for (Contact contact : contacts){
            if (contact.getName().equalsIgnoreCase(fio)){
                contact.setGroup(name);
            }
        }
        XmlMapper mapper = new XmlMapper();
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
        mapper.writeValue(file,contactList);

        Set<models.Contact> contacts1 = new TreeSet<>();
        model.update(this,contacts1);
        return result;
    }

    @Override
    public boolean removeGroupContact(String fio) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {
        boolean result  = false;
        File file = new File("contacts.xml");
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contactList = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);

        List<Contact> contacts = contactList.getContact();
        for (Contact contact : contacts){
            if (contact.getName().equalsIgnoreCase(fio)){
                contact.setGroup("нет группы");
            }
        }
        XmlMapper mapper = new XmlMapper();
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
        mapper.writeValue(file,contactList);

        Set<models.Contact> contacts1 = new TreeSet<>();
        model.update(this,contacts1);
        return result;
    }

    @Override
    public Set<models.Contact> getContacts() throws ParserConfigurationException,
            SAXException, IOException, XPathExpressionException {
        Set<models.Contact> contactsSet = new TreeSet<>();
        ObjectMapper objectMapper = new XmlMapper();
        Contacts contacts = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contactsList = contacts.getContact();
        for (Contact contact : contactsList){
            models.Contact contact1 = new models.Contact();
            contact1.setFio(contact.getName());
            contact1.setGroup(new Group(contact.getGroup()));
            contact1.setPhone(contact.getPhone());
            contact1.setEmail(contact.getEmail());
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
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contactsList = contacts.getContact();
        for (Contact contact : contactsList){
            if (contact.getName().equalsIgnoreCase(fio)){
                contact1 = new models.Contact();
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
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contactsList = contacts.getContact();
        for (Contact contact : contactsList){
            if (contact.getName().equalsIgnoreCase(fio)){
                name = contact.getName();
            }
        }
        return name;
    }

}
