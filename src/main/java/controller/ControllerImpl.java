package controller;

import dao.DirectoryDaoImpl;
import models.Contact;
import models.Entity;
import models.Group;
import service.ContactServiceImpl;
import service.GroupServiceImpl;
import storage.RefBook;
import utilits.TeamList;

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
        this.serviceContact = new ContactServiceImpl(this.refBook);
        this.serviceGroup = new GroupServiceImpl(this.refBook);
    }

    @Override
    public void addEntity(List<String> attrEntity, String command) throws IOException {
        Entity entity = creatingEntity(command,attrEntity.get(0));
        if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
            this.serviceContact.addContact(attrEntity,entity);


        if (command.equalsIgnoreCase(String.valueOf(TeamList.gro)))
            this.serviceGroup.addGroup(entity);
    }

    Entity creatingEntity(String entity,String name) throws IOException{
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.con))) return new Contact(name);
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.gro))) return new Group(name);
        throw new IOException();
    }



    @Override
    public void updContact(Contact contact) {
          this.serviceContact.updContact(contact);
    }

    @Override
    public void remContact(String fio) {
        this.serviceContact.remContact(fio);
    }

    @Override
    public void appGroupContact(Contact contact){
            this.serviceContact.appGroupContact(contact);
    }

    @Override
    public void remGroupContact() {
        this.serviceContact.remGroupContact();
    }

    @Override
    public void contactInf() {
        this.serviceContact.contactInf();
    }

    @Override
    public Set<Contact> getContacts() {
        Set<Contact> contacts =  this.serviceContact.getContacts();
        return contacts;
    }

    public Contact getContact (String fio){
        return this.serviceContact.getContact(fio);
    }












    @Override
    public void listGroupContact() {
        this.serviceGroup.listGroupContact();
    }

    @Override
    public Set<Group> getGroups() {
        return this.serviceGroup.getGroups();
    }

    @Override
    public void remGroup() throws IOException {
        this.serviceGroup.remGroup();
    }

    @Override
    public void updGroup() throws IOException {
            this.serviceGroup.updGroup();
    }

}
