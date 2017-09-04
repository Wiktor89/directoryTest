package net.directory.service;

import net.directory.dao.ContactDao;
import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *Сервис для группы
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    /**
     * console
     * refBook
     * contacts
     * groups
     * net.directory.dao
     */
    
    private ContactDao dao;
    
//    public ContactServiceImpl() {
//        this.dao = ContactsDaoImpl.getContactsDaoImpl();
//    }
    @Autowired(required = true)
    @Qualifier(value = "contactDao")
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
