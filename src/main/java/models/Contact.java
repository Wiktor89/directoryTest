package models;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Модель контакта
 */
public class Contact implements Serializable,Entity {

    /**
     * fio
      phone
      email
      group
     */
    private int id;
    private String fio;
    private String phone;
    private String email;
    private Group group;
    private final static AtomicInteger COUNT = new AtomicInteger(0);

    public Contact(String fio) {
        this(fio,"нет информации","нет информации");
    }

    public Contact(String fio, String phone, String email) {
        this.id = COUNT.incrementAndGet();
        this.fio = fio;
        this.phone = phone;
        this.email = email;
    }

    public Contact() {
        this("нет данных","нет данных","нет данных");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @return Ф.И.О контакта
     */
    public String contactInf(){
        return  "Ф И О  "+getFio();
    }

    @Override
    public String toString() {
        return "id "+ getId()+" Contact{" +
                "Ф И О = " + fio   +
                ", телефон = " + phone +
                ", email = " + email +
                ", группа = " + group.informationGroup() +
                "} ";
    }
}
