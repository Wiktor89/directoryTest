package net.directory.service;

import net.directory.dao.GroupDao;
import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.Group;
import net.directory.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Set;

/**
 *Сервис для контакта
 */
@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {

    /**
     *consol
     * refBook
     * contacts
     * groups
     * net.directory.dao
     */
    GroupDao dao;

    @Autowired(required = true)
    @Qualifier(value = "groupDao")
    public void setDao(GroupDao dao) {
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
    @Transactional
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
    public boolean removeGroup(Integer id) throws SQLException {
            return this.dao.removeGroup(id);
    }
    
    
}
