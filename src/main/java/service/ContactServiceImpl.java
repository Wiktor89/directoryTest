package service;

import dao.DomSaxContactsParser;
import dao.parsers.dom.DomContactParserImp;
import dao.parsers.dom.DomGroupParserImp;
import models.Contact;
import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *Сервис для группы
 */
public class ContactServiceImpl implements ContactService{

    /**
     *consol
     * refBook
     * contacts
     * groups
     * dao
     */
    private DomSaxContactsParser dao = null;

    public ContactServiceImpl(DomSaxContactsParser dao) {
        this.dao = dao;
    }

    public DomSaxContactsParser getDao() {
        return dao;
    }

    public void setDao(DomSaxContactsParser dao) {
        this.dao = dao;
    }

    @Override
    public void addContact(Entity entity) throws IOException, ParserConfigurationException,
            SAXException, TransformerException {
        this.dao.addContact(entity);
    }

    @Override
    public void updateContact(List<String> attContact) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {
        this.dao.updateContact(attContact);
    }

    @Override
    public void removeContact(String fio) throws SAXException, TransformerException,
            ParserConfigurationException, IOException {
        this.dao.removeContact(fio);

    }

    @Override
    public void appGroupContact(List<String> attContact) throws SAXException, TransformerException,
            ParserConfigurationException, IOException {
        this.dao.appGroupContact(attContact);
    }

    @Override
    public void removeGroupContact(String fio) throws ParserConfigurationException,
            SAXException, IOException, TransformerException {
        this.dao.removeGroupContact(fio);
    }

    @Override
    public Set<Contact> getContacts() throws ParserConfigurationException, SAXException,
            XPathExpressionException, IOException {
        return this.dao.getContacts();
    }

    @Override
    public boolean existContact(String name) throws ParserConfigurationException,
            TransformerException, SAXException, IOException {
        return this.dao.existContact(name);
    }

    @Override
    public Contact getContact(String fio) throws IOException, SAXException, ParserConfigurationException {
        return this.dao.getContact(fio);
    }

    @Override
    public String searchName(String fio) throws IOException, SAXException,
            ParserConfigurationException {
        return this.dao.searchName(fio);
    }

}
