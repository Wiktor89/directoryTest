package service;

import dao.DomSaxContactsParser;
import dao.parsers.dom.DomContactParserImp;
import models.Contact;
import models.Entity;
import models.Group;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
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
    private DomSaxContactsParser dao = new DomContactParserImp();

    public ContactServiceImpl() {
    }

    public DomSaxContactsParser getDao() {
        return dao;
    }

    public void setDao(DomSaxContactsParser dao) {
        this.dao = dao;
    }

    @Override
    public void addContact(Entity entity) {
        this.dao.addContact(entity);
    }

    @Override
    public void updateContact(List<String> attContact) {
        this.dao.updateContact(attContact);
    }

    @Override
    public void removeContact(String fio) {
        this.dao.removeContact(fio);

    }

    @Override
    public void appGroupContact(List<String> attContact){
        this.dao.appGroupContact(attContact);
    }

    @Override
    public void removeGroupContact(String fio) {
        this.dao.removeGroupContact(fio);
    }

    @Override
    public Set<Contact> getContacts() throws ParserConfigurationException, SAXException,
            XPathExpressionException, IOException {
        return this.dao.getContacts();
    }

    @Override
    public boolean existContact(String name) {
        return this.dao.existContact(name);
    }

    @Override
    public Contact getContact(String fio) {
        return this.dao.getContact(fio);
    }

    @Override
    public Contact searchName(String fio) {
        return null;
    }

}
