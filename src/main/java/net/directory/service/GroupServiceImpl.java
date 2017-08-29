package net.directory.service;

import net.directory.dao.hibernate.GroupsDaoImp;
import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.Group;
import net.directory.models.User;

import java.sql.SQLException;
import java.util.Set;

/**
 *Сервис для контакта
 */
public class GroupServiceImpl implements GroupService {

    /**
     *consol
     * refBook
     * contacts
     * groups
     * net.directory.dao
     */
    private GroupsDaoImp dao;
    
    public GroupServiceImpl() {
        this.dao = GroupsDaoImp.getGroupsDaoImpl();
    }
    
    public void setDao(GroupsDaoImp dao) {
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
    
    @Override
    public boolean removeGroup(Integer id) {
            return this.dao.removeGroup(id);
    }
    
    
}
