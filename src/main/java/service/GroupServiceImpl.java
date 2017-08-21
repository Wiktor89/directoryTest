package service;

import dao.GroupDao;
import factories.DaoFactory;
import models.Contact;
import models.Entity;
import models.Group;
import models.User;

import java.sql.SQLException;
import java.util.*;

/**
 *Сервис для контакта
 */
public class GroupServiceImpl implements GroupService {

    /**
     *consol
     * refBook
     * contacts
     * groups
     * dao
     */
    private GroupDao dao = null;

    public GroupServiceImpl() {
        this.dao = DaoFactory.getGroupDao();
    }


    @Override
    public Set<Contact> getContactsGroup(String name) throws SQLException {
        return this.dao.getContactsGroup(name);
    }

    @Override
    public Set<Group> getGroups() {
        return this.dao.getGroups();
    }

    @Override
    public void addGroup(Entity entity) throws SQLException {
        this.dao.addGroup(entity);
    }
    
    
    public boolean removeGroup(Integer id) throws SQLException {
        return this.dao.removeGroup(id);
    }

    @Override
    public boolean updateGroup(Integer id, String name) throws SQLException {
        return this.dao.updateGroup(id, name);
    }

    @Override
    public boolean existGroup(Integer id) throws SQLException {
        return this.dao.existGroup(id);
    }
    
    @Override
    public Integer numberUsers() throws SQLException {
        return this.dao.numberUsers();
    }
    
    @Override
    public Integer numberContacts(String name) throws SQLException {
        return this.dao.numberContacts(name);
    }
    
    @Override
    public Integer quantityGroupsUser(String name) throws SQLException {
        return this.dao.quantityGroupsUser(name);
    }
    
    @Override
    public Integer averageNumberContactsGroups() throws SQLException {
        return this.dao.averageNumberContactsGroups();
    }
    
    @Override
    public Integer averageNumberContactsUser() throws SQLException {
        return this.dao.averageNumberContactsUser();
    }
    
    @Override
    public Set<User> userWithContactsMin_10() throws SQLException {
        return this.dao.userWithContactsMin_10();
    }
}
