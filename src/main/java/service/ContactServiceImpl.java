package service;

import dao.DirectoryDaoImpl;
import models.Contact;
import models.Entity;
import models.Group;
import storage.RefBook;
import views.ViewImpl;

import java.io.EOFException;
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
        Contact contact = (Contact) entity;
        Set<Contact> contacts = refBook.getContacts();
        List<String> list = view.addContact();
        String fioContact = list.get(0);
        String phone = list.get(1);
        String email = list.get(2);
        if (fioContact.trim().length() > 0 && phone.trim().length() > 0
                && email.trim().length() > 0){
            contact.setFio(fioContact);
            contact.setPhone(phone);
            contact.setEmail(email);
        }else if (fioContact.trim().length() > 0){
            contact.setFio(fioContact);
        }else {
            throw new IOException();
        }
        contacts.add(contact);
        contact.setGroup(new Group(view.getNoGroup()));//Наблюдатель
        view.getSuc();
        this.dao.save(refBook);
    }

    @Override
    public void updContact() throws IOException {
        String fioContact = view.getEntContact();
        Set<Contact> contacts = refBook.getContacts();
       for (Contact contact : contacts){
           if (contact.getFio().equalsIgnoreCase(fioContact)){
               view.getContactInfo(contact);//Возможно будит полезным видить старые данные
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
                           view.getListGroup(group);
                       }
                       String nameGroup = view.getEntGroup();
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
    public void remContact() {
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            view.getListContacts(contact);
        }
        String fioContact = view.getEntContact();
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()){
            Contact contact = iterator.next();
            if (contact.getFio().equalsIgnoreCase(fioContact)){
                iterator.remove();
                view.getSuc();
            }
        }
        this.dao.save(refBook);
    }

    @Override
    public void appGroupContact() throws IOException {
        Set<Contact> contacts  = refBook.getContacts();
        Set<Group> groups = refBook.getGroups();
        if (!groups.isEmpty()){
            for (Group group : groups){
                view.getListGroup(group);
            }
        }else {
            throw new EOFException();
        }
        String name = view.getEntContact();
        for (Contact contact : contacts){
            if (contact.getFio().equalsIgnoreCase(name)){
                name = view.getEntGroup();
                if (existGroups(name)){
                    Group group = contact.getGroup();
                    group.setName(name);
                }else {
                    throw new IOException();
                }
            }
            view.getSuc();
        }
        this.dao.save(refBook);
    }

    @Override
    public void remGroupContact() {
        String nameContact = view.getEntContact();
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts) {
            if (contact.getFio().equalsIgnoreCase(nameContact)) {
                view.getContactInfo(contact);
                String nameGroup = view.getEntGroup();
                Group group = contact.getGroup();
                if (group.getName().equalsIgnoreCase(nameGroup)) {
                    group.setName(view.getNoGroup());//Наблюдатель
                    view.getSuc();
                }
            }
            this.dao.save(refBook);
        }
    }

    @Override
    public void contactInf() {
        String fioContact = view.getEntContact();
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            if (contact.getFio().equalsIgnoreCase(fioContact)){
                view.getContactInfo(contact);
            }
        }
    }

    @Override
    public void listContacts() {
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            view.getListContacts(contact);
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
