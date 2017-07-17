package controller;

import models.Contact;
import service.ContactServiceImpl;
import service.GroupServiceImpl;
import utilits.ConsoleReader;
import views.View;

import java.io.IOException;

/**
 *Реализация контроллера.
 * Контроллер дергает сервис в котором вся логика
 */
public class ControllerImpl implements Controller{
    /**
     * Поля инициализируются в контарукторе
     */
    private View view = null;
    private ContactServiceImpl serviceContact = null;
    private ConsoleReader consol = null;
    private GroupServiceImpl serviceGroup = null;

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

    public ControllerImpl(ContactServiceImpl serviceContact, ConsoleReader reader, GroupServiceImpl serviceGroup) {
        this.serviceContact = serviceContact;
        this.serviceGroup = serviceGroup;
        this.view = new View();
        this.consol = reader;
    }

    /**
     * Добавляем контакт
     */
    @Override
    public void addContact() {
        try {
            this.serviceContact.addContact();
        } catch (IOException e) {
            System.out.println("Вы не ввели Ф И О");
            addContact();
        }
    }

    /**
     * Обновляем контакт
     */
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

    /**
     * Удаляем контакт
     */
    @Override
    public void removeContact() {

        this.serviceContact.removeContact();
        view.pageActionContact();
    }

    /**
     * Добавляем группу контакту
     */
    @Override
    public void appGroupContact() {
        System.out.println("Введите ФИО контакта которому хотите присвоить группу");
        this.serviceContact.appGroupContact(this.consol.readString());
    }

    /**
     * Удаляем группу у контакта
     */
    @Override
    public void removeGroupContact() {
        System.out.println("Введите контакт у которого хотите удалить группу");
        this.serviceContact.removeGroupContact(this.consol.readString());
    }

    /**
     * Инф. о контакте
     */
    @Override
    public void informationContact() {
        System.out.println("Введите ФИО контакта по которому хотите инф.");
        this.serviceContact.informationContact(this.consol.readString());
    }

    /**
     * Список контактов
     */
    @Override
    public void listContacts() {
        this.serviceContact.listContacts();
    }

    /**
     * Список контактов опр. группы
     */
    @Override
    public void listGroupContact() {
        System.out.println("Введите группу для просмотра контактов");
        this.serviceGroup.listGroupContact(this.consol.readString());
    }

    /**
     * Список групп
     */
    @Override
    public void listGroup() {
        this.serviceGroup.listGroup();

    }

    /**
     * Добавление группы
     */
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

    /**
     * Обновление группы
     */
    @Override
    public void updateGroup() {
        System.out.println("Введите имя группы которую хотите обновить");
        this.serviceGroup.updateGroup(this.consol.readString());
    }

    /**
     * Стартовая страница
     */
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

    /**
     * Действия для контакта
     */
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

    /**
     * Действия для группы
     */
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
