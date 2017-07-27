package models;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Модель группы
 */
public class Group extends IdentifiedEntity implements Serializable {

    /**
     * name
     * contact
     */
    private int id;
    private String name;
    private Contact contact;

    public Group() {
    }

    public Group(String name) {
        super();
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
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
        return +id+" имя группы - "+ name;
    }

    /**
     * @return имя группы
     */
    public String informationGroup(){
        return name;
    }
}
