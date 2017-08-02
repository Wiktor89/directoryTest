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
import java.util.TreeSet;

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
            addContact("con");
        if (command.equalsIgnoreCase(String.valueOf(TeamList.rem)))
            removeContact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.upd)))
            updateContact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.list)))
            getContacts();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.addg)))
            appGroupContact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.remg)))
            removeGroupContact();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.inf)))
            getContactInfo();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.ser)))
            searchName();
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
            } catch (TransformerException | ParserConfigurationException | SAXException e) {
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
                try {
                    controller.removeContact(fio);
                } catch (SAXException | TransformerException | IOException | ParserConfigurationException e) {
                    System.out.println("не поддерживается");
                }
                view.getSuc();
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
        try {
            if (this.controller.existContact(name)){
                System.out.println("Введите новое Ф И О");
                attContact.add(1,this.consol.readString());
                if (attContact.get(1).trim().length() > 0){
                    System.out.println("Введите новый телефон");
                    attContact.add(2,this.consol.readString());
                    System.out.println("Введите новый email");
                    attContact.add(3,this.consol.readString());
                    try {
                        this.controller.updateContact(attContact);
                    } catch (ParserConfigurationException | IOException | SAXException e) {
                        System.out.println("не поддерживается");
                    }
                    view.getSuc();
                }else {
                    System.out.println("Вы не ввели Ф И О");
                }
            }else {
                System.out.println("нет контакта");
            }
        } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    void appGroupContact() {
        getContacts();
        List<String> attContact = new ArrayList<>();
        String fio = getNameContact();
        try {
            if (this.controller.existContact(fio)){
                getGroups();
                String name = getNameGroup();
                try {
                    if (this.controller.existGroup(name)){
                        attContact.add(0, fio);
                        attContact.add(1, name);
                        this.controller.appGroupContact(attContact);
                    }else {
                        view.getNoGroup();
                    }
                } catch (XPathExpressionException e) {
                    System.out.println("не поддерживается");
                }
            }else {
                view.notFound();
            }
        } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
            System.out.println("не поддерживается");
        }
    }

    @Override
    public void getContactInfo() {
        getContacts();
        Contact contact = null;
        try {
            contact = this.controller.getContact(getNameContact());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        if (!(contact == null)){
            System.out.println(contact);
        }else {
            view.getNoContact();
        }
    }

    void removeGroupContact() {
        getContacts();
        String fio = getNameContact();
        try {
            if (this.controller.existContact(fio)){
                Contact contact = this.controller.getContact(fio);
                String name = getNameGroup();
                try {
                    if (this.controller.existGroup(name)){
                        try {
                            try {
                                this.controller.removeGroupContact(fio);
                            } catch (TransformerException e) {
                                System.out.println("a");
                            }
                        } catch (IOException | ParserConfigurationException | SAXException e) {
                            System.out.println("b");
                        }
                    }
                } catch (XPathExpressionException e) {
                    System.out.println("removeGroupContact");
                }
            }else {
                view.getNoContact();
            }
        } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
            System.out.println("не поддерживается");
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
                } catch (TransformerException | ParserConfigurationException | SAXException e) {
                    e.printStackTrace();
                }
                view.getSuc();
            } catch (IOException e) {
                System.out.println("не поддерживается");
            }
        }else {
            System.out.println("не ввели имя группы");
        }
    }

    public void removeGroup() {
        getGroups();
        String name = getNameGroup();
        if(name.trim().length() > 0){
            try {
                if (this.controller.existGroup(name)){
                    try {
                        this.controller.removeGroup(name);
                    } catch (ParserConfigurationException | TransformerException | XPathExpressionException | SAXException | IOException e) {
                        e.printStackTrace();
                    }
                    view.getSuc();
                }else {
                    view.getNoGroup();
                }
            } catch (ParserConfigurationException | IOException | SAXException e) {
                System.out.println("не поддерживается");
            } catch (XPathExpressionException e) {
                System.out.println("removeGroup");
            }
        }else {
            view.emptyLine();
        }
    }

    public void updateGroup() {
        getGroups();
        List<String> attGroup = new ArrayList<>();
        String oldName = getNameGroup();
        try {
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
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println("не поддерживается");
        } catch (XPathExpressionException e) {
            System.out.println("updateGroup");
        }
    }

    @Override
    public void getGroups(){
        System.out.println("Список доступных групп");
        try {
            Set<String> groups = new TreeSet<>();
            groups = this.controller.getGroups();
            if (!groups.isEmpty()){
                for (String group : groups){
                    System.out.println(group);
                }
            }
        } catch (XPathExpressionException | IOException | SAXException | ParserConfigurationException | TransformerConfigurationException e) {
            System.out.println("нет доступных групп");
            view.pageActionContact();
        }
    }

    public void getContactsGroup(){
        getGroups();
        Set<String> contacts = new TreeSet<>();
        try {
            contacts = this.controller.getContactsGroup(getNameGroup());
        } catch (ParserConfigurationException | IOException | XPathExpressionException | SAXException e) {
            System.out.println("не найденно");
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
    public void searchName() {
        try {
            String s = this.controller.searchName(getNameContact());
            if (s.trim().length() > 0){
                System.out.println("найден контакт "+s);
            }else {
                System.out.println("нет контакта");
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
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
