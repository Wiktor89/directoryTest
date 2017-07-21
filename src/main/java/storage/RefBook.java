package storage;

import models.Contact;
import models.Group;
import sorted.ContactFioComparator;
import sorted.GroupNameComparator;
import views.*;
import views.Observer;

import java.io.Serializable;
import java.util.*;

/**
 *Модель для справочника контактов
 */
public class RefBook implements  Serializable {

    private int id;
    private Set<Contact> contacts = new TreeSet<>(new ContactFioComparator());
    private Set<Group> groups = new TreeSet<>(new GroupNameComparator());
    private List<Observer> subscriber = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Observer> getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(List<Observer> subscriber) {
        this.subscriber = subscriber;
    }

    public void notifyObserver (){
        for (Observer observer : subscriber){
            observer.handle(getContacts(),getGroups());
        }
    }
}
