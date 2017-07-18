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
    private DirectoryDaoImpl dao = new DirectoryDaoImpl();

    public ContactServiceImpl(RefBook refBook,ConsoleReader reader) {
        this.refBook = refBook;
        this.consol = reader;
    }

    public DirectoryDaoImpl getDao() {
        return dao;
    }
    public void setDao(DirectoryDaoImpl dao) {
        this.dao = dao;
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

    @Override
    public void addContact() throws IOException {
        Set<Contact> contacts = refBook.getContacts();
        Contact contact = null;
        System.out.println("Введите ФИО");
        String fioContact = this.consol.readString();
        System.out.println("Введите телефон");
        String phone = this.consol.readString();
        System.out.println("Введите email");
        String email = this.consol.readString();
        if (fioContact.trim().length() > 0 && phone.trim().length() > 0
                && email.trim().length() > 0){
            contact = new Contact(fioContact,phone,email);
        }else if (fioContact.trim().length() > 0){
            contact = new Contact(fioContact);
        }else {
            throw new IOException();
        }
        contact.setGroup(new Group("нет группы"));
        System.out.println("Контакт успешно добавлен");
        contacts.add(contact);
        this.dao.save(refBook);
    }

    @Override
    public void updateContact(String fioContact) throws IOException {
        Set<Contact> contacts = refBook.getContacts();
       for (Contact contact : contacts){
           if (contact.getFio().equalsIgnoreCase(fioContact)){
               System.out.println(contact);//Возможно будит полезным видить старые данные
               System.out.println("Введите новое ФИО");
               String newFio = this.consol.readString();
               if (newFio.trim().length() != 0){
                   contact.setFio(newFio);
                   System.out.println("Введите новый phone");
                   contact.setPhone(this.consol.readString());
                   System.out.println("Введите новый email");
                   contact.setEmail(this.consol.readString());
                   boolean result = true;
                   while (result){
                       System.out.println("Доступные группы");
                       Set<Group> groups = refBook.getGroups();
                       for (Group group : groups){
                           System.out.println(group.getName());
                       }
                       System.out.println("Введите название группы");
                       String nameGroup = this.consol.readString();
                       for (Group group : groups){
                           if (group.getName().equalsIgnoreCase(nameGroup)){
                               contact.setGroup(new Group(nameGroup));
                               result = false;
                           }
                       }
                   }
               }else {
                   throw new IOException();
               }
           }
       }
        this.dao.save(refBook);
    }

    @Override
    public void removeContact() {
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            System.out.println(contact.informationContact());
        }
        System.out.println("Введите ФИО контакта для удаления");
        String fioContact = this.consol.readString();
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()){
            Contact contact = iterator.next();
            if (contact.getFio().equalsIgnoreCase(fioContact)){
                iterator.remove();
            }
        }
        this.dao.save(refBook);
    }

    @Override
    public void appGroupContact(String fioContact) throws Exception {
        Set<Contact> contacts = refBook.getContacts();
        Set<Group> groups = refBook.getGroups();
        if (!groups.isEmpty()){
            System.out.println("Доступные группы");
            for (Group group : groups){
                System.out.println(group);
            }
            for (Contact contact : contacts){
                if (contact.getFio().equalsIgnoreCase(fioContact)){
                    System.out.println("Введите имя группы");
                    String nameGroup = this.consol.readString();
                    if (existGroups(nameGroup)){
                        Group group = contact.getGroup();
                        group.setName(nameGroup);
                    }else {
                        throw new Exception();
                    }
                }
            }
        }else {
            throw new Exception();
        }
        this.dao.save(refBook);
    }

    @Override
    public void removeGroupContact(String nameContact) {
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts) {
            if (contact.getFio().equalsIgnoreCase(nameContact)) {
                System.out.println(contact.toString());
                System.out.println("Введите имя удаляемой группы");
                String nameGroup = this.consol.readString();
                Group group = contact.getGroup();
                if (group.getName().equalsIgnoreCase(nameGroup)) {
                    group.setName("нет группы");
                }
            }
            this.dao.save(refBook);
        }
    }

    @Override
    public void informationContact(String fioContact) {
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            if (contact.getFio().equalsIgnoreCase(fioContact)){
                System.out.println(contact);
            }
        }
    }

    @Override
    public void listContacts() {
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            System.out.println(contact.informationContact());
        }
    }

    @Override
    public boolean existGroups(String nameGroup){
        Set<Group> groups = refBook.getGroups();
        boolean result = false;
        for (Group group : groups){
            if (group.getName().equalsIgnoreCase(nameGroup)){
                result = true;
            }
        }
        return result;
    }
}
