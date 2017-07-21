package service;

import dao.DirectoryDaoImpl;
import models.Contact;
import models.Entity;
import models.Group;
import storage.RefBook;

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
        contact.setFio(attrEntity.get(0));
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
    public void updContact(List<String> attContact) {
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            contact.setEmail("нет информации");
            contact.setPhone("нет информации");
            if (contact.getFio().equalsIgnoreCase(attContact.get(0))){
                contact.setFio(attContact.get(1));
                if (attContact.get(2).trim().length() > 0) contact.setPhone(attContact.get(2));
                if (attContact.get(3).trim().length() > 0) contact.setEmail(attContact.get(3));
                this.dao.save(refBook);
            }
        }
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
    public void appGroupContact(List<String> attContact){
        Set<Contact> contacts  = refBook.getContacts();
        for (Contact contact : contacts){
            if (contact.getFio().equalsIgnoreCase(attContact.get(0))){
                Group group = contact.getGroup();
                group.setName(attContact.get(1));
                this.dao.save(refBook);
            }
        }
    }

    @Override
    public void remGroupContact(String fio) {
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            if (contact.getFio().equalsIgnoreCase(fio)){
                Group group = contact.getGroup();
                group.setName("нет группы");
            }
            this.dao.save(refBook);
        }
    }

    @Override
    public Set<Contact> getContacts() {
        Set<Contact> contacts = refBook.getContacts();
        return contacts;
    }

    @Override
    public boolean existContact(String name) {
        boolean result = false;
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            if (contact.getFio().equalsIgnoreCase(name)){
                result = true;
            }
        }
        return result;
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
