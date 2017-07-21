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
    public void addEntity(List<String> attrEntity, String command) throws IOException  {
        Entity entity = creatingEntity(command);
        if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
            this.serviceContact.addContact(attrEntity,entity);


        if (command.equalsIgnoreCase(String.valueOf(TeamList.gro)))
            this.serviceGroup.addGroup(entity);
    }

    Entity creatingEntity(String entity) throws IOException{
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.con))) return new Contact();
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.gro))) return new Group();
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
    }//Возвращает список контактов


    @Override
    public Contact getContact (String fio){
        return this.serviceContact.getContact(fio);
    }//Возвращает контакт

    @Override
    public boolean existContact(String name) {
        return this.serviceContact.existContact(name);
    }

    @Override
    public boolean existGroup(String name) {
        return this.serviceGroup.existGroup(name);
    }

    public Group getGroup1(String name){
        return serviceGroup.getGroup(name);
    }//Возвращает группу












    @Override
    public Set<Contact> getContactsGroup(String name) {
       return this.serviceGroup.getContactsGroup(name);
    }

    @Override
    public Set<Group> getGroups() {
        return this.serviceGroup.getGroups();
    }

    public Group getGroup (String name){
        return this.serviceGroup.getGroup(name);
    }

    @Override
    public void remGroup(String name)  {
        this.serviceGroup.remGroup(name);
    }

    @Override
    public void updGroup(Group group,String oldName)  {
            this.serviceGroup.updGroup(group,oldName);
    }


}
