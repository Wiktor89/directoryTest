package models;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Модель группы
 */
public class Group extends IdentifiedEntity implements Serializable,Comparable<Group> {

    /**
     * name
     * contact
     */
    private String name;
    private Contact contact;

    public Group()  {
    }

    public Group(String name) {
        super();
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return " имя группы - "+ name;
    }

    /**
     * @return имя группы
     */
    public String informationGroup(){
        return name;
    }

    @Override
    public int compareTo(Group o) {
        return name.compareToIgnoreCase(o.getName());
    }
}
