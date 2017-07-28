package dao.parsers.sax;

import dao.DomSaxContactsParser;
import dao.parsers.Parser;
import models.Contact;
import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class ContactParserImpl extends Parser implements DomSaxContactsParser {

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
    public Set<String> getContacts() throws ParserConfigurationException,
            SAXException, IOException, XPathExpressionException {
        return super.getContacts();
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
