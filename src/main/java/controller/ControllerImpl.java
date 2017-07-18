package controller;

import dao.DirectoryDaoImpl;
import service.ContactServiceImpl;
import service.GroupServiceImpl;
import storage.RefBook;
import utilits.ConsoleReader;
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
    public void addContact() {
        try {
            this.serviceContact.addContact();
        } catch (IOException e) {
            System.out.println("Вы не ввели Ф И О");
            addContact();
        }
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
    public void addGroup() {
        System.out.println("Введите имя группы");
        try {
            this.serviceGroup.addGroup(this.consol.readString());
        } catch (IOException e) {
            System.out.println("Вы не ввели имя группы");
            addGroup();
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
            if (command.equalsIgnoreCase("con")){
                actionContacts();
            }else if (command.equalsIgnoreCase("gro")){
                actionGroup();
            }else if (command.equalsIgnoreCase("exit")){
                System.exit(0);
            }else {
                System.out.println("Команда не поддерживается");
                startPage();
            }
        }
    }

    @Override
    public void actionContacts(){
        String command = view.pageActionContact();
        if (command.equalsIgnoreCase("addc")){
            addContact();
        }else if (command.equalsIgnoreCase("remc")){
            removeContact();
        }else if (command.equalsIgnoreCase("updc")){
            updateContact();
        }else if (command.equalsIgnoreCase("listc")){
            listContacts();
        }else if (command.equalsIgnoreCase("addcatg")){
            appGroupContact();
        }else if (command.equalsIgnoreCase("remcofg")){
            removeGroupContact();
        }else if (command.equalsIgnoreCase("inf")){
            informationContact();
        }else if (command.equalsIgnoreCase("up")){
            startPage();
        }else {
            System.out.println("Не поддерживается");
        }
    }

    @Override
    public void actionGroup(){
        String command = view.pageActionGroup();
        if (command.equalsIgnoreCase("addg")){
            addGroup();
        }else if (command.equalsIgnoreCase("remg")){
            removeGroup();
        }else if (command.equalsIgnoreCase("updg")){
            updateGroup();
        }else  if (command.equalsIgnoreCase("listg")){
            listGroup();
        }else if (command.equalsIgnoreCase("listcofg")){
            listGroupContact();
        }else if (command.equalsIgnoreCase("up")){
            startPage();
        }else {
            System.out.println("Команда не поддерживается");
        }
    }

}
