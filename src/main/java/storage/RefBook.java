package storage;

import models.Contact;
import models.Group;
import sorted.ContactFioComparator;
import sorted.GroupNameComparator;
import views.ViewChangModel;

import java.io.Serializable;
import java.util.*;

/**
 *Модель для справочника контактов
 */
public class RefBook extends Observable implements  Serializable {

    private int id;
    private Set<Contact> contacts = new TreeSet<>(new ContactFioComparator());
    private Set<Group> groups = new TreeSet<>(new GroupNameComparator());
    private List<Observer> observers = new ArrayList<>();
    private ViewChangModel model = null;

    public RefBook() {
        this.model = new ViewChangModel();
        this.observers.add(model);
    }

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

    public void register(Observer observer){
        this.observers.add(observer);
    }

    public void notification (){
        for (Observer observer : observers){
            observer.update(this, getContacts());
        }
    }
}
