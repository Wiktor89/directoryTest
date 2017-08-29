package net.directory.models;

import javax.persistence.*;
import java.util.Set;

/**
 *Модель группы
 */
@javax.persistence.Entity
@Table(name = "groups")
public class Group extends IdentifiedEntity implements Comparable<Group> {

    /**
     * name
     * contact
     */
    @Column(name = "title")
    private String name;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
    private Set<Contact> contact;

    public Group()  {
    }

    public Group(String name) {
        super();
        this.name = name;
    }
    
    
    public Set<Contact> getContact() {
        return contact;
    }
    
    public void setContact(Set<Contact> contact) {
        this.contact = contact;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }

    @Override
    public int compareTo(Group group) {
        return this.getId() - group.getId();
    }
}
