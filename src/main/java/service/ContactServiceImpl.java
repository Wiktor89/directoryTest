package service;

import dao.DirectoryDaoImpl;
import models.Contact;
import models.Entity;
import models.Group;
import storage.RefBook;
import utilits.ConsoleReader;
import views.ViewImpl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
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
    private RefBook refBook = null;
    private DirectoryDaoImpl dao = new DirectoryDaoImpl();
    private ViewImpl view = new ViewImpl();

    public ContactServiceImpl(RefBook refBook) {
        this.refBook = refBook;
    }

    public DirectoryDaoImpl getDao() {
        return dao;
    }
    public void setDao(DirectoryDaoImpl dao) {
        this.dao = dao;
    }
    public RefBook getRefBook() {
        return refBook ;
    }
    public void setRefBook(RefBook refBook) {
            this.refBook = refBook;
    }

    @Override
    public void addContact(Entity entity) throws IOException {
        Contact contact = null;
        Set<Contact> contacts = refBook.getContacts();
        List<String> list = view.addContact();
        String fioContact = list.get(0);
        String phone = list.get(1);
        String email = list.get(2);
        if (fioContact.trim().length() > 0 && phone.trim().length() > 0
                && email.trim().length() > 0){
            contact = new Contact(fioContact,phone,email);
        }else if (fioContact.trim().length() > 0){
            contact = new Contact(fioContact);
        }else {
            throw new IOException();
        }
        contact.setGroup(new Group(view.noGroup()));//Наблюдатель
        System.out.println("Контакт успешно добавлен");
        this.dao.save(refBook);
    }

    @Override
    public void updateContact() throws IOException {
        String fioContact = view.entContact();
        Set<Contact> contacts = refBook.getContacts();
       for (Contact contact : contacts){
           if (contact.getFio().equalsIgnoreCase(fioContact)){
               view.informationContact(contact);//Возможно будит полезным видить старые данные
               List<String> contactsNew = view.updateContact();
               String newFio = contactsNew.get(0);
               if (newFio.trim().length() != 0){
                   contact.setFio(newFio);
                   contact.setPhone(contactsNew.get(1));
                   contact.setEmail(contactsNew.get(2));
                   boolean result = true;
                   while (result){
                       Set<Group> groups = refBook.getGroups();
                       for (Group group : groups){
                           view.listGroup(group);
                       }
                       String nameGroup = view.entGroup();
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
            view.listContacts(contact);
        }
        String fioContact = view.entContact();
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
    public void appGroupContact() throws Exception {
        Set<Contact> contacts = refBook.getContacts();
        Set<Group> groups = refBook.getGroups();
        if (!groups.isEmpty()){
            for (Group group : groups){
                view.listGroup(group);
            }
            String name = view.entContact();
            for (Contact contact : contacts){
                if (contact.getFio().equalsIgnoreCase(name)){
                    String nameGroup = view.entGroup();
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
    public void removeGroupContact() {
        String nameContact = view.entContact();
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts) {
            if (contact.getFio().equalsIgnoreCase(nameContact)) {
                view.informationContact(contact);
                String nameGroup = view.entGroup();;
                Group group = contact.getGroup();
                if (group.getName().equalsIgnoreCase(nameGroup)) {
                    group.setName(view.noGroup());//Наблюдатель
                }
            }
            this.dao.save(refBook);
        }
    }

    @Override
    public void informationContact() {
        String fioContact = view.entContact();
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            if (contact.getFio().equalsIgnoreCase(fioContact)){
                view.informationContact(contact);
            }
        }
    }

    @Override
    public void listContacts() {
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            view.listContacts(contact);
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
