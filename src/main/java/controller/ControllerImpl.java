package controller;

import dao.DirectoryDaoImpl;
import factory.ContactsFactory;
import factory.EntityFactory;
import factory.GroupFactory;
import models.Contact;
import models.Entity;
import service.ContactServiceImpl;
import service.GroupServiceImpl;
import storage.RefBook;
import utilits.ConsoleReader;
import utilits.TeamList;
import views.View;

import java.io.IOException;

/**
 *Реализация контроллера
 */
public class ControllerImpl implements Controller{

    /**
     * Поля инициализируются в конcтрукторе
     */
    private View view = null;
    private ContactServiceImpl serviceContact = null;
    private GroupServiceImpl serviceGroup = null;
    private ConsoleReader consol = null;
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
    public ConsoleReader getConsol() {
        return consol;
    }
    public void setConsol(ConsoleReader consol) {
        this.consol = consol;
    }
    public ContactServiceImpl getServiceContact() {
        return serviceContact;
    }
    public void setServiceContact(ContactServiceImpl serviceContact) {
        this.serviceContact = serviceContact;
    }
    public View getView() {
        return view;
    }
    public void setView(View view) {
        this.view = view;
    }

    public ControllerImpl(ConsoleReader reader) {
        this.refBook = this.dao.load();
        this.serviceContact = new ContactServiceImpl(this.refBook, reader);
        this.serviceGroup = new GroupServiceImpl(this.refBook, reader);
        this.view = new View();
        this.consol = reader;
    }

    @Override
    public void updateContact() {
        System.out.println("Введите ФИО контакта который хотите обновить");
        try {
            this.serviceContact.updateContact(this.consol.readString());
        } catch (IOException e) {
            System.out.println("Вы не ввели Ф И О");
            updateContact();
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
        System.out.println("Введите ФИО контакта которому хотите присвоить группу");
        try {
            this.serviceContact.appGroupContact(this.consol.readString());
        } catch (Exception e) {
            System.out.println("нет групп");
            actionContacts();
        }
    }

    @Override
    public void removeGroupContact() {
        System.out.println("Введите контакт у которого хотите удалить группу");
        this.serviceContact.removeGroupContact(this.consol.readString());
    }

    @Override
    public void informationContact() {
        System.out.println("Введите ФИО контакта по которому хотите инф.");
        this.serviceContact.informationContact(this.consol.readString());
    }

    @Override
    public void listContacts() {
        this.serviceContact.listContacts();
    }

    @Override
    public void listGroupContact() {
        System.out.println("Введите группу для просмотра контактов");
        this.serviceGroup.listGroupContact(this.consol.readString());
    }

    @Override
    public void listGroup() {
        try {
            this.serviceGroup.listGroup();
        } catch (Exception e) {
            System.out.println("нет групп");
            actionGroup();
        }

    }

    @Override
    public void removeGroup() {
        System.out.println("Введите имя удаляемой группы");
        this.serviceGroup.removeGroup(this.consol.readString());
    }

    @Override
    public void updateGroup() {
        System.out.println("Введите имя группы которую хотите обновить");
        try {
            this.serviceGroup.updateGroup(this.consol.readString());
        } catch (IOException e) {
            System.out.println("Вы не ввели имя новой группы");
            actionGroup();
        }
    }

    @Override
    public void startPage() {
        String command = view.startPage();
        while (true){
            if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))actionContacts();
            if (command.equalsIgnoreCase(String.valueOf(TeamList.gro)))actionGroup();
            if (command.equalsIgnoreCase(String.valueOf(TeamList.exit)))System.exit(0);
            System.out.println("Команда не поддерживается");
            startPage();
        }
    }

    @Override
    public void actionContacts(){
        String command = view.pageActionContact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addc)))addEntity();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remc)))removeContact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listc)))listContacts();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addcatg)))appGroupContact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remcofg)))removeGroup();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.inf)))informationContact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))startPage();
        System.out.println("Команда не поддерживается");
        actionContacts();
    }

    @Override
    public void actionGroup(){
        String command = view.pageActionGroup();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addg)))addEntity();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remg)))removeGroup();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.updc)))updateGroup();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listg)))listGroup();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listcofg)))listGroupContact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))startPage();
        System.out.println("Команда не поддерживается");
        actionGroup();
    }

    @Override
    public void addEntity() {
        String command = this.consol.readString();
        try {
            EntityFactory factory = creatingEntityFactory(command);
            Entity entity = factory.creatingEntity();
            if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
                this.serviceContact.addContact(entity);
            if (command.equalsIgnoreCase(String.valueOf(TeamList.gro)))
                this.serviceGroup.addGroup(entity);
        } catch (IOException e) {
            System.out.println("не поддерживается");
        }

    }

    static EntityFactory creatingEntityFactory (String entity) throws IOException{
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.con))) return new ContactsFactory();
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.gro))) return new GroupFactory();
        throw new IOException();
    }

}
