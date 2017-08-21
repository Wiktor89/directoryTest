package models;

import java.io.Serializable;

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
        return  name;
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
