package views;

import controller.ControllerImpl;
import models.Contact;
import org.xml.sax.SAXException;
import utilits.ConsoleReader;
import utilits.TeamList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
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
            removeContact();//Наблюдатель Удаление контакта...
        if (command.equalsIgnoreCase(String.valueOf(TeamList.upd)))
            updateContact();//Обновление контакта...
        if (command.equalsIgnoreCase(String.valueOf(TeamList.list)))
            getContacts();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addg)))
            appGroupContact();//Наблюдатель
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remg)))
            removeGroupContact();//Наблюдатель
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
            try {
                this.controller.addEntity(attContact,command);
            } catch (TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
            }
            view.getSuc();
        }else {
            view.getNoContact();
        }
    }

    public void removeContact()  {
        getContacts();
        String fio = getNameContact();
        if (fio.trim().length() > 0){
            if (this.controller.existContact(fio)){
                controller.removeContact(fio);
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
    public void updateContact()  {
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
                this.controller.updateContact(attContact);
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

    void removeGroupContact() {
        getContacts();
        String fio = getNameContact();
        if (this.controller.existContact(fio)){
            Contact contact = this.controller.getContact(fio);
            System.out.println(contact);
            String name = getNameGroup();
            if (this.controller.existGroup(name)){
                this.controller.removeGroupContact(fio);
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
            removeGroup();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.upd)))
            updateGroup();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.list)))
            getGroups();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.listc)))
            getContactsGroup();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.up)))view.startPage();
        view.pageActionGroup();
    }

    public void addGroup (String command) {
        List<String> attGroup = new ArrayList<>();
        String name = getNameGroup();
        if (name.trim().length() > 0){
            attGroup.add(name);
            try {
                try {
                    this.controller.addEntity(attGroup,command);
                } catch (TransformerException | ParserConfigurationException e) {
                    e.printStackTrace();
                }
                view.getSuc();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("не ввели имя группы");
        }
    }

    public void removeGroup() {
        getGroups();
        String name = getNameGroup();
        if(name.trim().length() > 0){
            if (this.controller.existGroup(name)){
                try {
                    this.controller.removeGroup(name);
                } catch (ParserConfigurationException | TransformerException | XPathExpressionException | SAXException e) {
                    e.printStackTrace();
                }
                view.getSuc();
            }else {
                view.getNoGroup();
            }
        }else {
            view.emptyLine();
        }
    }

    public void updateGroup() {
        getGroups();
        List<String> attGroup = new ArrayList<>();
        String oldName = getNameGroup();
        if (this.controller.existGroup(oldName)){
            String newName = getNameGroup();
            if (newName.trim().length() > 0){
                attGroup.add(0,oldName);
                attGroup.add(1,newName);
                try {
                    this.controller.updateGroup(attGroup);
                } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
                    e.printStackTrace();
                }
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
        Set<String> groups = null;
        try {
            groups = this.controller.getGroups();
        } catch (XPathExpressionException | IOException | SAXException | ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        }
        if (!groups.isEmpty()){
            for (String group : groups){
                System.out.println(group);
            }
        }else {
            view.emptyList();
        }
    }

    public void getContactsGroup(){
        getGroups();
        Set<String> contacts = null;
        try {
            contacts = this.controller.getContactsGroup(getNameGroup());
        } catch (ParserConfigurationException | IOException | XPathExpressionException | SAXException e) {
            e.printStackTrace();
        }
        for (String contact : contacts){
            System.out.println(contact);
        }
    }

    @Override
    public String getNameGroup() {
        System.out.println("Введите имя группы (обязательное поле)");
        return this.consol.readString();
    }

    @Override
    public void getContacts(){
        Set<Contact> contacts = null;
        try {
            contacts = this.controller.getContacts();
        } catch (ParserConfigurationException | IOException | XPathExpressionException | SAXException e) {
            e.printStackTrace();
        }
        for (Contact contact : contacts){
            System.out.println(contact.contactInf());
        }
    }

}
