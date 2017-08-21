package service;

import dao.ContactDao;
import factories.DaoFactory;
import models.Contact;
import models.Entity;
import models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *Сервис для группы
 */
public class ContactServiceImpl implements ContactService{

    /**
     * console
     * refBook
     * contacts
     * groups
     * dao
     */
    private ContactDao dao = null;

    public ContactServiceImpl() {
        this.dao = DaoFactory.getContactDao();
    }

    public ContactDao getDao() {
        return dao;
    }

    public void setDao(ContactDao dao) {
        this.dao = dao;
    }

    @Override
    public void addContact(Entity entity) throws SQLException {
        this.dao.addContact(entity);
    }

    @Override
    public void updateContact(List<String> attContact) throws SQLException {
        this.dao.updateContact(attContact);
    }

    @Override
    public void removeContact(Integer id) throws SQLException {
        this.dao.removeContact(id);

    }

    @Override
    public void appGroupContact(List<String> attContact) throws SQLException {
        this.dao.appGroupContact(attContact);
    }

    @Override
    public void removeGroupContact(List<String> attr) throws SQLException {
        this.dao.removeGroupContact(attr);
    }

    @Override
    public Set<Contact> getContacts() throws SQLException {
        return this.dao.getContacts();
    }

    @Override
    public boolean existContact(String name) throws SQLException {
        return this.dao.existContact(name);
    }

    @Override
    public Contact getContact(String fio) throws SQLException {
        return this.dao.getContact(fio);
    }

    @Override
    public Contact searchName(String fio) throws SQLException {
        return this.dao.searchName(fio);
    }
    
    @Override
    public User authorizationPage(List<String> attr) throws SQLException {
        return this.dao.authorizationPage(attr);
    }
    
}
