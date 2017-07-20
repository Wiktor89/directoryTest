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
    public void updateContact() throws IOException {
            this.serviceContact.updateContact();
    }

    @Override
    public void removeContact() {
        this.serviceContact.removeContact();
    }

    @Override
    public void appGroupContact() throws IOException {
            this.serviceContact.appGroupContact();
    }

    @Override
    public void removeGroupContact() {
        this.serviceContact.removeGroupContact();
    }

    @Override
    public void informationContact() {
        this.serviceContact.informationContact();
    }

    @Override
    public void listContacts() {
        this.serviceContact.listContacts();
    }

    @Override
    public void listGroupContact() {
        this.serviceGroup.listGroupContact();
    }

    @Override
    public void listGroup() throws IOException {
            this.serviceGroup.listGroup();
    }

    @Override
    public void removeGroup() {
        this.serviceGroup.removeGroup();
    }

    @Override
    public void updateGroup() throws IOException {
            this.serviceGroup.updateGroup();
    }

    @Override
    public void addEntity(String command) throws IOException {
            Entity entity = creatingEntity(command);
            if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
                this.serviceContact.addContact(entity);
            if (command.equalsIgnoreCase(String.valueOf(TeamList.gro)))
                this.serviceGroup.addGroup(entity);
    }

     Entity creatingEntity(String entity) throws IOException{
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.con))) return new Contact();
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.gro))) return new Group();
        throw new IOException();
    }

}
