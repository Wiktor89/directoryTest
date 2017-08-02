package dao.sax;

import dao.ContactDao;
import dao.sax.handler.ContactHandler;
import models.Contact;
import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *Sax парсера для контакта
 */
public class ContactsImpl implements ContactDao {

    @Override
    public void addContact(Entity entity) throws IOException {
        throw new IOException();
    }

    @Override
    public boolean updateContact(List<String> attContact) throws IOException {
        throw new IOException();
    }

    @Override
    public boolean removeContact(String fio) throws IOException {
        throw new IOException();
    }

    @Override
    public boolean appGroupContact(List<String> attContact) throws IOException {
        throw new IOException();
    }

    @Override
    public boolean removeGroupContact(String fio) throws IOException {
        throw new IOException();
    }

    @Override
    public Set<Contact> getContacts() throws ParserConfigurationException,
            SAXException, IOException, XPathExpressionException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        ContactHandler contactHandler = new ContactHandler();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("contacts.xml"), contactHandler);
        Set<Contact> contacts = contactHandler.getContacts();
        return contacts;
    }

    @Override
    public boolean existContact(String name) throws ParserConfigurationException, SAXException, IOException {
        boolean result = false;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        ContactHandler contactHandler = new ContactHandler();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("contacts.xml"), contactHandler);
        Set<Contact> contacts = contactHandler.getContacts();
        for (Contact contact : contacts) {
            if (contact.getFio().equalsIgnoreCase(name)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Contact getContact(String fio) throws ParserConfigurationException, SAXException, IOException {
        try {
            Set<Contact> contacts = getContacts();
            for (Contact contact : contacts){
                if (contact.getFio().equalsIgnoreCase(fio)){
                    return contact;
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String searchName(String fio) throws ParserConfigurationException,
            SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        ContactHandler contactHandler = new ContactHandler();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("contacts.xml"), contactHandler);
        Set<Contact> contacts = contactHandler.getContacts();
        for (Contact contact : contacts) {
            if (contact.getFio().equalsIgnoreCase(fio)) {
                return contact.getFio();
            }
        }
        return null;
    }

}
