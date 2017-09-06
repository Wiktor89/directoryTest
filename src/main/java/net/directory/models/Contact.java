package net.directory.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 *Модель контакта
 */
@javax.persistence.Entity
@Table(name = "contacts")
public class Contact extends IdentifiedEntity implements Comparable<Contact> {

    /**
     * fio
      phone
      email
      group
     */
    @Column(name = "fio")
    private String fio;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "email")
    private String email;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToMany
    @JoinTable(name = "contact_group", joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private Set<Group> groups;

    public Contact() {
    }
    
    public Contact(String fio) {
        this(fio,"нет информации","нет информации");
    }
    
    public Contact(String fio, String phone, String email) {
        this.fio = fio;
        this.phone = phone;
        this.email = email;
    }
    
    public Contact(String fio, String phone) {
        this(fio,phone,"нет информации");
    }
    
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Set<Group> getGroups() {
        return groups;
    }
    
    public void setGroup(Set<Group> group) {
        this.groups = group;
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
    

    /**
     * @return Ф.И.О контакта
     */
    public String contactInf(){
        return  "Ф И О  "+getFio();
    }

    @Override
    public String toString() {
        return "Contact{ id "  +getId()+
                " Ф И О = " + fio+
                ", телефон = " + phone +
                ", email = " + email + "; группы " +groups+
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
        return groups != null ? groups.equals(contact.groups) : contact.groups == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    

    @Override
    public int compareTo(Contact o) {
        return this.getId() - o.getId();
    }
}
