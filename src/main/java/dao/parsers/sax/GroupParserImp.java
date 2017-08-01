package dao.parsers.sax;

import dao.DomSaxGroupParser;
import dao.parsers.handler.HandlerContact;
import dao.parsers.handler.HandlerGroup;
import models.Contact;
import models.Entity;
import models.Group;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 *Реализация Sax парсера для группы
 */
public class GroupParserImp implements DomSaxGroupParser {


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
        HandlerContact handlerContact = new HandlerContact();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("contacts"), handlerContact);
        Set<Contact> contacts = handlerContact.getContacts();
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
        HandlerGroup handlerGroup = new HandlerGroup();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("groups.xml"), handlerGroup);
        Set<String> groups = handlerGroup.getGroups();
        return groups;
    }

    @Override
    public Set<String> getContactsGroup(String name) throws IOException, SAXException,
            ParserConfigurationException {
        Set<String> contactsS = new TreeSet<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        HandlerContact handlerContact = new HandlerContact();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("contacts"), handlerContact);
        Set<Contact> contacts = handlerContact.getContacts();
        for (Contact contact : contacts){
            Group group = contact.getGroup();
            if (group.getName().equalsIgnoreCase(name)){
                contactsS.add(contact.getFio());
            }
        }
        return contactsS;
    }


}
