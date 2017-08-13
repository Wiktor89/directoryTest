package service;

import dao.GroupDao;
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

    public GroupServiceImpl(GroupDao dao) {
        this.dao = dao;
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

    @Override
    public boolean removeGroup(String name) throws SQLException {
        return this.dao.removeGroup(name);
    }

    @Override
    public boolean updateGroup(List<String> attGroup) throws SQLException {
        return this.dao.updateGroup(attGroup);
    }

    @Override
    public boolean existGroup(String name) throws SQLException {
        return this.dao.existGroup(name);
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
