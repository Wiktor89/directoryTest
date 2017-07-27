package dao.parsers.dom;

import dao.DomSaxContactsParser;
import models.Contact;
import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class ContactParserImp implements DomSaxContactsParser {

    @Override
    public boolean addContact(Entity entity) {
        return false;
    }

    @Override
    public boolean updContact(List<String> attContact) {
        return false;
    }

    @Override
    public boolean remContact(String fio) {
        return false;
    }

    @Override
    public boolean appGroupContact(List<String> attContact) {
        return false;
    }

    @Override
    public boolean remGroupContact(String fio) {
        return false;
    }

    @Override
    public Set<Contact> getContacts() throws ParserConfigurationException, SAXException, IOException {
        return null;
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
