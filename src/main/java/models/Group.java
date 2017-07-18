package models;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Модель группы
 */
public class Group implements Serializable {

    /**
     * name
     * contact
     */
    private int id;
    private String name;
    private Contact contact;
    private final static AtomicInteger COUNT = new AtomicInteger(0);

    public Group(String name) {
        this.id = COUNT.incrementAndGet();
        this.name = name;
    }

    public int getId() {
        return id;
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
        return "id "+ getId()+" имя группы - "+ name;
    }

    /**
     * @return имя группы
     */
    public String informationGroup(){
        return name;
    }
}
