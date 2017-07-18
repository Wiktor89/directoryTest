package storage;

import models.Contact;
import models.Group;
import sorted.ContactFioComparator;
import sorted.GroupNameComparator;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 *Серелизуемый объект
 */
public class RefBook implements  Serializable {

    private int id;
    private Set<Contact> contacts = new TreeSet<>(new ContactFioComparator());
    private Set<Group> groups = new TreeSet<>(new GroupNameComparator());

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

}
