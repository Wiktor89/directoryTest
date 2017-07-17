package service;

import dao.DirectoryDaoImpl;
import models.Contact;
import models.Group;
import storage.RefBook;
import utilits.ConsoleReader;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 *Сервис для группы
 */
public class ContactServiceImpl implements ContactService{

    /**
     *consol
     * refBook
     * contacts
     * groups
     * dao
     */
    private ConsoleReader consol = null;
    private RefBook refBook = null;
    private Set<Contact> contacts = null;
    private Set<Group> groups = null;
    private DirectoryDaoImpl dao = new DirectoryDaoImpl();

    public ContactServiceImpl() {
        this.refBook = this.dao.load();
        this.contacts = this.refBook.getContacts();
        this.groups = this.refBook.getGroups();
        this.consol = new ConsoleReader();
    }


    public DirectoryDaoImpl getDao() {
        return dao;
    }
    public void setDao(DirectoryDaoImpl dao) {
        this.dao = dao;
    }
    public Set<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
    public Set<Group> getGroups() {
        return groups;
    }
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
    public ConsoleReader getConsol() {
        return consol;
    }
    public void setConsol(ConsoleReader consol) {
        this.consol = consol;
    }
    public RefBook getRefBook() {
        return refBook ;
    }
    public void setRefBook(RefBook refBook) {
            this.refBook = refBook;
    }

    /**
     *Добавление контакта
     */
    @Override
    public void addContact() throws IOException {
        System.out.println("Введите ФИО");
        String fioContact = this.consol.readString();
        System.out.println("Введите телефон");
        String phone = this.consol.readString();
        System.out.println("Введите email");
        String email = this.consol.readString();
        if (fioContact.trim().length() > 0){
            Contact contact = new Contact(fioContact,phone,email);
            this.contacts.add(contact);
            System.out.println("Контакт успешно добавлен");
            this.dao.save(refBook);
        }else {
            throw new IOException();
        }
    }

    /**
     *Обновление контакта
     */
    @Override
    public void updateContact(String fioContact) {
       for (Contact contact : this.contacts){
           if (contact.getFio().equalsIgnoreCase(fioContact)){
               System.out.println(contact);//Возможно будит полезным видить старые данные
               System.out.println("Введите новое ФИО");
               contact.setFio(this.consol.readString());
               System.out.println("Введите новый phone");
               contact.setPhone(this.consol.readString());
               System.out.println("Введите новый email");
               contact.setEmail(this.consol.readString());
               boolean result = true;
               while (result){
                   System.out.println("Доступные группы");
                   for (Group group : this.groups){
                       System.out.println(group.getNameGroup());
                   }
                   System.out.println("Введите название группы");
                   String nameGroup = this.consol.readString();
                   for (Group group : this.groups){
                       if (group.getNameGroup().equalsIgnoreCase(nameGroup)){
                           Group group1 = contact.getGroup();
                           group1.setNameGroup(nameGroup);
                           result = false;
                       }
                   }
               }
           }
       }
        this.dao.save(refBook);
    }

    /**
     *Удаление контакта
     */
    @Override
    public void removeContact() {
        for (Contact contact : this.contacts){
            System.out.println(contact.informationContact());
        }
        System.out.println("Введите ФИО контакта для удаления");
        String fioContact = this.consol.readString();
        Iterator<Contact> iterator = this.contacts.iterator();
        while (iterator.hasNext()){
            Contact contact = iterator.next();
            if (contact.getFio().equalsIgnoreCase(fioContact)){
                iterator.remove();
            }
        }
        this.dao.save(refBook);
    }

    /**
     *Добавить контакту группу
     */
    @Override
    public void appGroupContact(String fioContact) {
        for (Contact contact : this.contacts){
            if (contact.getFio().equalsIgnoreCase(fioContact)){
                System.out.println("Введите имя группы");
                String nameGroup = this.consol.readString();
                if (existGroups(nameGroup)){
                    Group group = contact.getGroup();
                    group.setNameGroup(nameGroup);
                }else {
                    System.out.println("Такой группы нет");
                }
            }
        }
        this.dao.save(refBook);
    }

    /**
     *Удаление группы у контакта контакта
     */
    @Override
    public void removeGroupContact(String nameContact) {
        for (Contact contact : this.contacts) {
            if (contact.getFio().equalsIgnoreCase(nameContact)) {
                System.out.println(contact.toString());
                System.out.println("Введите имя удаляемой группы");
                String nameGroup = this.consol.readString();
                Group group = contact.getGroup();
                if (group.getNameGroup().equalsIgnoreCase(nameGroup)) {
                    group.setNameGroup("Нет группы");
                }
            }
            this.dao.save(refBook);
        }
    }

    /**
     *Инф. по контакту
     */
    @Override
    public void informationContact(String fioContact) {
        for (Contact contact : this.contacts){
            if (contact.getFio().equalsIgnoreCase(fioContact)){
                System.out.println(contact);
            }
        }
    }

    /**
     *Список контактов
     */
    @Override
    public void listContacts() {
        for (Contact contact : this.contacts){
            System.out.println(contact.informationContact());
        }
    }

    @Override
    public boolean existGroups(String nameGroup){
        boolean result = false;
        for (Group group : this.groups){
            if (group.getNameGroup().equalsIgnoreCase(nameGroup)){
                result = true;
            }
        }
        return result;
    }



}
