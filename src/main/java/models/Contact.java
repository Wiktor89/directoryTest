package models;

/**
 *Модель контакта
 */
public class Contact extends IdentifiedEntity {

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

    public Contact(String fio, String phone, String email) {
        super();
        this.fio = fio;
        this.phone = phone;
        this.email = email;
    }

    public Contact(String fio) {
        this(fio,"нет информации","нет информации");

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
     *
     * @return Ф.И.О контакта
     */
    public String informationContact(){
        return  " Ф И О  "+getFio();
    }

    /**
     *
     * @return всю инф. о контакте
     */
    @Override
    public String toString() {
        return " Contact{" +
                "Ф И О = " + fio   +
                ", телефон = " + phone +
                ", email = " + email +
                ", группа = " + group +
                "} ";
    }
}
