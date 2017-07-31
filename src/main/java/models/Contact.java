package models;

import java.io.Serializable;

/**
 *Модель контакта
 */
public class Contact extends IdentifiedEntity implements Serializable,Comparable<Contact> {

    /**
     * fio
      phone
      email
      group
     */
    private String fio;
    private String phone;
    private String email;
    private Group group;

    public Contact() {
    }

    public Contact(String fio) {
        this(fio,"нет информации","нет информации");
    }

    public Contact(String fio, String phone, String email) {
        super();
        this.fio = fio;
        this.phone = phone;
        this.email = email;
    }

    public Contact(String fio,String phone) {
        this(fio,phone,"нет информации");
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
        return "ContactJac{" +
                "Ф И О = " + fio   +
                ", телефон = " + phone +
                ", email = " + email +
                ", группа = "  +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (fio != null ? !fio.equals(contact.fio) : contact.fio != null) return false;
        if (phone != null ? !phone.equals(contact.phone) : contact.phone != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        return group != null ? group.equals(contact.group) : contact.group == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void setId(int id) {

    }

    @Override
    public int compareTo(Contact o) {
        return fio.compareToIgnoreCase(o.getFio());
    }
}
