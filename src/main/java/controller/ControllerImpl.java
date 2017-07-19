package controller;

import dao.DirectoryDaoImpl;
import factory.ContactsFactory;
import factory.EntityFactory;
import factory.GroupFactory;
import models.Entity;
import service.ContactServiceImpl;
import service.GroupServiceImpl;
import storage.RefBook;
import utilits.TeamList;
import views.ViewImpl;

import java.io.IOException;

/**
 *Реализация контроллера
 */
public class ControllerImpl implements Controller{

    /**
     * Поля инициализируются в конcтрукторе
     */
    private ViewImpl view  = new ViewImpl();
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
    public void updateContact() {
        try {
            this.serviceContact.updateContact();
        } catch (IOException e) {
            System.out.println("Вы не ввели Ф И О");//Это будит делать наблюдатель
            view.pageActionContact();
        }
        view.pageActionContact();
    }

    @Override
    public void removeContact() {
        this.serviceContact.removeContact();
        view.pageActionContact();
    }

    @Override
    public void appGroupContact() {
        try {
            this.serviceContact.appGroupContact();
        } catch (Exception e) {
            System.out.println("нет групп");
            view.pageActionContact();
        }
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
    public void listGroup() {
        try {
            this.serviceGroup.listGroup();
        } catch (Exception e) {
            System.out.println("нет групп");
            view.pageActionGroup();
        }

    }

    @Override
    public void removeGroup() {
        this.serviceGroup.removeGroup();
    }

    @Override
    public void updateGroup() {
        try {
            this.serviceGroup.updateGroup();
        } catch (IOException e) {
            System.out.println("Вы не ввели имя новой группы");//Наблюдатель
            view.actionGroup();
        }
    }

    @Override
    public void addEntity(String command) {
        try {
            EntityFactory factory = creatingEntityFactory(command);
            Entity entity = factory.creatingEntity();
            if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
                this.serviceContact.addContact(entity);
            if (command.equalsIgnoreCase(String.valueOf(TeamList.gro)))
                this.serviceGroup.addGroup(entity);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    public EntityFactory creatingEntityFactory (String entity) throws IOException{
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.con))) return new ContactsFactory();
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.gro))) return new GroupFactory();
        throw new IOException();
    }

}
