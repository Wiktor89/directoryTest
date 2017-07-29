package dao.parsers.sax;

import dao.DomSaxContactsParser;
import dao.parsers.handler.HandlerContact;
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
 *
 */
public class ContactParserImpl implements DomSaxContactsParser {

    @Override
    public boolean addContact(Entity entity) {
        return false;
    }

    @Override
    public boolean updateContact(List<String> attContact) {
        return false;
    }

    @Override
    public boolean removeContact(String fio) {
        return false;
    }

    @Override
    public boolean appGroupContact(List<String> attContact) {
        return false;
    }

    @Override
    public boolean removeGroupContact(String fio) {
        return false;
    }

    @Override
    public Set<Contact> getContacts() throws ParserConfigurationException,
            SAXException, IOException, XPathExpressionException {
        SAXParserFactory factory = SAXParserFactory.newInstance();//фабрика
        HandlerContact handlerContact = new HandlerContact();
        SAXParser parser = factory.newSAXParser();//получаем парсер
        parser.parse(new File("refbook.xml"), handlerContact);//читаем из файла, слушатель
        Set<Contact> contacts = handlerContact.getContacts();
        return contacts;
    }

    @Override
    public boolean existContact(String name) {
        return false;
    }

    @Override
    public Contact getContact(String fio) {
        return null;
    }

    @Override
    public Contact searchName(String fio) {
        return null;
    }
}
