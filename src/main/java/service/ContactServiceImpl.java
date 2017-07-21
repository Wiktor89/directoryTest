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
    public void addContact(List<String> attrEntity, Entity entity) {
        Contact contact = (Contact) entity;
        Set<Contact> contacts = refBook.getContacts();
        contact.setGroup(new Group(attrEntity.get(3)));
        if (attrEntity.get(1).trim().length() > 0 ||
                attrEntity.get(2).trim().length() > 0) {
            contact.setPhone(attrEntity.get(1));
            contact.setEmail(attrEntity.get(2));
        }
        contacts.add(contact);
        this.dao.save(refBook);
    }

    @Override
    public void updContact(Contact contact) {
        Set<Contact> contacts = refBook.getContacts();
        contacts.add(contact);
        this.dao.save(refBook);
    }

    @Override
    public void remContact(String fio) {
        Set<Contact> contacts = refBook.getContacts();
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()){
            Contact contact = iterator.next();
            if (contact.getFio().equalsIgnoreCase(fio)){
                iterator.remove();
            }
        }
        this.dao.save(refBook);
    }

    @Override
    public void appGroupContact(Contact contact){
        Set<Contact> contacts  = refBook.getContacts();
        contacts.add(contact);
        this.dao.save(refBook);
    }

    @Override
    public void remGroupContact(Contact contact) {
        Set<Contact> contacts = refBook.getContacts();
        contacts.add(contact);
        this.dao.save(refBook);
    }

    @Override
    public Set<Contact> getContacts() {
        Set<Contact> contacts = refBook.getContacts();
        return contacts;
    }

    public Contact getContact(String fio) {
        Contact contact1 = null;
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts) {
            if (contact.getFio().equalsIgnoreCase(fio)) {
                contact1 = contact;
            }
        }
        return contact1;
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











//
//    @Override
//    public void addObserver(Observer observer) {
//        List<Observer> subscribers = refBook.getSubscriber();
//        subscribers.add(observer);
//
//    }
//
//    @Override
//    public void remObserver(Observer observer) {
//        List<Observer> subscribers = refBook.getSubscriber();
//        subscribers.remove(observer);
//    }
//
//    @Override
//    public void notifyObserver() {
//        List<Observer> observers = refBook.getSubscriber();
//        for (Observer observer : observers){
//            observer.handle(refBook.getContacts());
//        }
//
//    }
}
