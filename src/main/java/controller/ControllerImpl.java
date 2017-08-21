package controller;

import factories.EntityFactory;
import models.Contact;
import models.Entity;
import models.Group;
import models.User;
import service.ContactServiceImpl;
import service.GroupServiceImpl;
import utilits.TeamList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *Реализация контроллера
 */
public class ControllerImpl implements Controller{

    /**
     * Поля инициализируются в конcтрукторе
     */
    private ContactServiceImpl serviceContact = null;
    private GroupServiceImpl serviceGroup = null;

    public GroupServiceImpl getServiceGroup() {
        return serviceGroup;
    }
    public void setServiceGroup(GroupServiceImpl serviceGroup) {
        this.serviceGroup = serviceGroup;
    }
    public ContactServiceImpl getServiceContact() {
        return serviceContact;
    }
    public void setServiceContact(ContactServiceImpl serviceContact) {
        this.serviceContact = serviceContact;
    }

    public ControllerImpl() {
        this.serviceContact = new ContactServiceImpl();
        this.serviceGroup = new GroupServiceImpl();
    }

    @Override
    public void addEntity(List<String> attrEntity,String command) throws IOException, SQLException {
        Entity entity = EntityFactory.create(command,attrEntity);
        if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
            this.serviceContact.addContact(entity);
        if (command.equalsIgnoreCase(String.valueOf(TeamList.gro)))
            this.serviceGroup.addGroup(entity);
    }

    @Override
    public void updateContact(List<String> attContact) throws SQLException {
          this.serviceContact.updateContact(attContact);
    }

    @Override
    public void removeContact(Integer id) throws SQLException {
        this.serviceContact.removeContact(id);
    }

    @Override
    public void appGroupContact(List<String> attContact) throws SQLException {
            this.serviceContact.appGroupContact(attContact);
    }

    @Override
    public void removeGroupContact(List<String> fio) throws SQLException {
        this.serviceContact.removeGroupContact(fio);
    }

    @Override
    public Set<Contact> getContacts() throws SQLException {
         return this.serviceContact.getContacts();
    }

    @Override
    public Contact getContact (String fio) throws SQLException {
        return this.serviceContact.getContact(fio);
    }

    @Override
    public boolean existContact(String name) throws SQLException {
        return this.serviceContact.existContact(name);
    }

    @Override
    public boolean existGroup(String name) throws SQLException {
        return this.serviceGroup.existGroup(name);
    }
    
    @Override
    public Integer numberUsers() throws SQLException {
        return this.serviceGroup.numberUsers();
    }
    
    @Override
    public Integer numberContacts(String name) throws SQLException {
        return this.serviceGroup.numberContacts(name);
    }
    
    @Override
    public Integer quantityGroupsUser(String name) throws SQLException {
        return this.serviceGroup.quantityGroupsUser(name);
    }
    
    @Override
    public Integer averageNumberContactsGroups() throws SQLException {
        return this.serviceGroup.averageNumberContactsGroups();
    }
    
    @Override
    public Integer averageNumberContactsUser() throws SQLException {
        return this.serviceGroup.averageNumberContactsUser();
    }
    
    @Override
    public Set<User> userWithContactsMin_10() throws SQLException {
        return this.serviceGroup.userWithContactsMin_10();
    }
    
    @Override
    public User authorizationPage(List<String> attr) throws SQLException {
        return this.serviceContact.authorizationPage(attr);
    }
    
    @Override
    public Set<Contact> getContactsGroup(String name) throws SQLException {
       return this.serviceGroup.getContactsGroup(name);
    }

    @Override
    public Set<Group> getGroups() {
        return this.serviceGroup.getGroups();
    }

    @Override
    public boolean removeGroup(String name) throws SQLException {
        return this.serviceGroup.removeGroup(name);
    }

    @Override
    public boolean updateGroup(List<String> attGroup) throws SQLException {
            return this.serviceGroup.updateGroup(attGroup, );
    }

}
