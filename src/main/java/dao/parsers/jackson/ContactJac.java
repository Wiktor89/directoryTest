package dao.parsers.jackson;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 *Модель контакта для jackson
 */

public final class ContactJac  {

    /**
     * title
     phone
     email
     group
     */
    @JacksonXmlProperty(localName = "id", isAttribute = true)
    private String id;
    @JacksonXmlProperty(localName = "title")
    private String title;
    @JacksonXmlProperty(localName = "phone")
    private String phone;
    @JacksonXmlProperty(localName = "email")
    private String email;
    @JacksonXmlProperty(localName = "group")
    private String group;



    public ContactJac() {
    }

    public ContactJac(String title) {
        this(title,"нет информации","нет информации");
    }

    public ContactJac(String title, String phone, String email) {
        this.title = title;
        this.phone = phone;
        this.email = email;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ContactJac{" +
                "title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
