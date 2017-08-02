package storage;

import models.Contact;
import models.Group;
import sorted.ContactFioComparator;
import sorted.GroupNameComparator;

import java.io.Serializable;
import java.util.*;

/**
 *Модель для справочника контактов
 */
public class RefBook extends Observable implements  Serializable {

    private static RefBook refBook;
    private Set<Contact> contacts = new TreeSet<>();
    private Set<Group> groups = new TreeSet<>();
    private List<Observer> observers = new ArrayList<>();

    public static RefBook getRefBook (){
        if (refBook == null){
            refBook = new RefBook();
        }
        return refBook;
    }

    private RefBook() {
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(Observer observers) {
        this.observers.add(observers);
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

    public void register(Observer observer){
        this.observers.add(observer);
    }

    public void notification (){
        for (Observer observer : observers){
            observer.update(this, getContacts());
        }
    }
}
