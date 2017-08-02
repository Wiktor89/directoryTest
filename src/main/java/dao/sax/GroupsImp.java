package dao.sax;

import dao.GroupDao;
import dao.sax.handler.ContactHandler;
import dao.sax.handler.GroupHandler;
import models.Contact;
import models.Entity;
import models.Group;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *Sax парсера для группы
 */
public class GroupsImp implements GroupDao {


    @Override
    public void addGroup(Entity entity) throws IOException {
        throw new IOException();
    }

    @Override
    public boolean removeGroup(String name) throws IOException  {
        throw new IOException();
    }

    @Override
    public void updateGroup(List<String> attGroup) throws IOException {
        throw new IOException();
    }

    @Override
    public boolean existGroup(String name) throws ParserConfigurationException,
            SAXException, IOException {
        boolean result = false;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        ContactHandler contactHandler = new ContactHandler();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("contacts"), contactHandler);
        Set<Contact> contacts = contactHandler.getContacts();
        for (Contact contact : contacts){
            Group group = contact.getGroup();
            if (group.getName().equalsIgnoreCase(name)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public Set<String> getGroups() throws ParserConfigurationException, SAXException
            , IOException, XPathExpressionException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        GroupHandler groupHandler = new GroupHandler();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("groups.xml"), groupHandler);
        Set<String> groups = groupHandler.getGroups();
        return groups;
    }

    @Override
    public Set<String> getContactsGroup(String name) throws IOException, SAXException,
            ParserConfigurationException {
        Set<String> contactsS = new TreeSet<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        ContactHandler contactHandler = new ContactHandler();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("contacts"), contactHandler);
        Set<Contact> contacts = contactHandler.getContacts();
        for (Contact contact : contacts){
            Group group = contact.getGroup();
            if (group.getName().equalsIgnoreCase(name)){
                contactsS.add(contact.getFio());
            }
        }
        return contactsS;
    }


}
