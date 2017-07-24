package views;

import controller.ControllerImpl;
import models.Contact;
import models.Group;
import utilits.ConsoleReader;
import utilits.TeamList;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *Отображение
 */
public class ViewImpl implements View {

    private ControllerImpl controller = null;
    private ConsoleReader consol = null;
    private ViewInf view = null;


    public ViewImpl() {
        this.controller = new ControllerImpl();
    }

    public ViewInf getView() {
        return view;
    }

    public void setView(ViewInf view) {
        this.view = view;
    }

    public ControllerImpl getController() {
        return this.controller;
    }
    public void setController(ControllerImpl controller) {
        this.controller = controller;
    }

    public ConsoleReader getConsol() {
        return consol;
    }
    public void setConsol(ConsoleReader consol) {
        this.consol = consol;
    }

    @Override
    public void actionContact() throws IOException {
        String command = this.consol.readString();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.add)))
            addContact("con");//Ввел добавить контакт
        if (command.equalsIgnoreCase(String.valueOf(TeamList.rem)))
            remContact();//Наблюдатель Удаление контакта...
        if (command.equalsIgnoreCase(String.valueOf(TeamList.upd)))
            updContact();//Обновление контакта...
        if (command.equalsIgnoreCase(String.valueOf(TeamList.list)))
            getContacts();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addg)))
            appGroupContact();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remg)))
            remGroupContact();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.inf)))
            getContactInfo();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))
            view.startPage();
        view.pageActionContact();
    }

    @Override
    public void addContact(String command) throws IOException   {
        List<String> attContact = new ArrayList<>();
        String fio = getNameContact();
        if (fio.trim().length() > 0){
            attContact.add(0,fio);
            System.out.println("Введите телефон");
            attContact.add(1,this.consol.readString());
            System.out.println("Введите email");
            attContact.add(2,this.consol.readString());
            attContact.add(3,"нет группы");
            this.controller.addEntity(attContact,command);
            view.getSuc();
        }else {
            view.getNoContact();
        }
    }

    public void remContact ()  {
        getContacts();
        String fio = getNameContact();
        if (fio.trim().length() > 0){
            if (this.controller.existContact(fio)){
                controller.remContact(fio);
                view.getSuc();
            }
        }else{
            view.getNoContact();
        }
    }

    @Override
    public String getNameContact()  {
        System.out.println("Введите Ф И О контакта (обязательное поле)");
        return  this.consol.readString();
    }

    @Override
    public void updContact()  {
        getContacts();
        List<String> attContact = new ArrayList<>();
        String name = getNameContact();
        attContact.add(0,name);
        if (this.controller.existContact(name)){
            System.out.println("Введите новое Ф И О");
            attContact.add(1,this.consol.readString());
            if (attContact.get(1).trim().length() > 0){
                System.out.println("Введите новый телефон");
                attContact.add(2,this.consol.readString());
                System.out.println("Введите новый email");
                attContact.add(3,this.consol.readString());
                this.controller.updContact(attContact);
                view.getSuc();
            }else {
                System.out.println("Вы не ввели Ф И О");
            }
        }else {
            System.out.println("нет контакта");
        }
    }

    void appGroupContact() {
        getContacts();
        List<String> attContact = new ArrayList<>();
        String fio = getNameContact();
        if (this.controller.existContact(fio)){
            getGroups();
            String name = getNameGroup();
            if (this.controller.existGroup(name)){
                attContact.add(0, fio);
                attContact.add(1, name);
                this.controller.appGroupContact(attContact);
            }else {
                view.getNoGroup();
            }
        }else {
            view.notFound();
        }
    }

    @Override
    public void getContactInfo() {
        getContacts();
        Contact contact = this.controller.getContact(getNameContact());
        if (!(contact == null)){
            System.out.println(contact);
        }else {
            view.getNoContact();
        }
    }

    void remGroupContact() {
        getContacts();
        String fio = getNameContact();
        if (this.controller.existContact(fio)){
            Contact contact = this.controller.getContact(fio);
            System.out.println(contact);
            String name = getNameGroup();
            if (this.controller.existGroup(name)){
                this.controller.remGroupContact(fio);
            }
        }else {
            view.getNoContact();
        }
    }

    @Override
    public void actionGroup()  {
        String command = this.consol.readString();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.add)))
            addGroup("gro");
        if (command.equalsIgnoreCase(String.valueOf(TeamList.rem)))
            remGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.upd)))
            updGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.list)))
            getGroups();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listc)))
            getContactsGroup();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))view.startPage();
        view.pageActionGroup();
    }

    public void addGroup (String command) {
        List<String> attGroup = new ArrayList<>();
        String name = getNameGroup();
        if (name.trim().length() > 0){
            attGroup.add(name);
            try {
                this.controller.addEntity(attGroup,command);
                view.getSuc();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("не ввели имя группы");
        }
    }

    public void remGroup() {
        getGroups();
        String name = getNameGroup();
        if(name.trim().length() > 0){
            if (this.controller.existGroup(name)){
                this.controller.remGroup(name);
                view.getSuc();
            }else {
                view.getNoGroup();
            }
        }else {
            view.emptyLine();
        }
    }

    public void updGroup() {
        getGroups();
        List<String> attGroup = new ArrayList<>();
        String oldName = getNameGroup();
        if (this.controller.existGroup(oldName)){
            String newName = getNameGroup();
            if (newName.trim().length() > 0){
                attGroup.add(0,oldName);
                attGroup.add(1,newName);
                this.controller.updGroup(attGroup);
            }else {
                view.emptyLine();
            }
        }else {
            view.getNoGroup();
        }
    }

    @Override
    public void getGroups(){
        System.out.println("Список доступных групп");
        Set<Group> groups = this.controller.getGroups();
        if (!groups.isEmpty()){
            for (Group group : groups){
                System.out.println(group);
            }
        }else {
            view.emptyList();
        }
    }

    public void getContactsGroup(){
        getGroups();
        Set<Contact> contacts = this.controller.getContactsGroup(getNameGroup());
        for (Contact contact : contacts){
            System.out.println(contact.contactInf());
        }
    }

    @Override
    public String getNameGroup() {
        System.out.println("Введите имя группы (обязательное поле)");
        return this.consol.readString();
    }

    @Override
    public void getContacts(){
        Set<Contact> contacts = this.controller.getContacts();
        for (Contact contact : contacts){
            System.out.println(contact.contactInf());
        }
    }

}
