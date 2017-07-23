package controller;

import dao.DirectoryDaoImpl;
import factory.EntityFactory;
import models.Contact;
import models.Entity;
import models.Group;
import service.ContactServiceImpl;
import service.GroupServiceImpl;
import storage.RefBook;
import utilits.TeamList;
import views.ViewChangModel;

import java.io.IOException;
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
    private RefBook refBook  = null;
    private DirectoryDaoImpl dao = new DirectoryDaoImpl();

    public RefBook getRefBook() {
        return refBook;
    }
    public void setRefBook(RefBook refBook) {
        this.refBook = refBook;
    }
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
        this.refBook = this.dao.load();
        this.refBook.setObservers(new ViewChangModel());
        this.serviceContact = new ContactServiceImpl(this.refBook);
        this.serviceGroup = new GroupServiceImpl(this.refBook);
    }

    @Override
    public void addEntity(List<String> attrEntity,String command) throws IOException  {
//        EntityFactory entityFactory = new EntityFactory();
        Entity entity = create(command);

        if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
            this.serviceContact.addContact(attrEntity,entity);

        if (command.equalsIgnoreCase(String.valueOf(TeamList.gro)))
            this.serviceGroup.addGroup(attrEntity,entity);
    }

     Entity create (String command) throws IOException{
        if (command.equalsIgnoreCase(String.valueOf(TeamList.con))) return new Contact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.gro))) return new Group();
        throw new IOException();
    }

    @Override
    public void updContact(List<String> attContact) {
          this.serviceContact.updContact(attContact);
    }

    @Override
    public void remContact(String fio) {
        this.serviceContact.remContact(fio);
    }

    @Override
    public void appGroupContact(List<String> attContact){
            this.serviceContact.appGroupContact(attContact);
    }

    @Override
    public void remGroupContact(String fio) {
        this.serviceContact.remGroupContact(fio);
    }

    @Override
    public Set<Contact> getContacts() {
        Set<Contact> contacts =  this.serviceContact.getContacts();
        return contacts;
    }

    @Override
    public Contact getContact (String fio){
        return this.serviceContact.getContact(fio);
    }

    @Override
    public boolean existContact(String name) {
        return this.serviceContact.existContact(name);
    }

    @Override
    public boolean existGroup(String name) {
        return this.serviceGroup.existGroup(name);
    }

    @Override
    public Set<Contact> getContactsGroup(String name) {
       return this.serviceGroup.getContactsGroup(name);
    }

    @Override
    public Set<Group> getGroups() {
        return this.serviceGroup.getGroups();
    }

    @Override
    public void remGroup(String name)  {
        this.serviceGroup.remGroup(name);
    }

    @Override
    public void updGroup(List<String> attGroup)  {
            this.serviceGroup.updGroup(attGroup);
    }

}
