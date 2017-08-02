package dao.parsers.jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

/**
 *Обертка для контактов
 */
@JacksonXmlRootElement(localName = "contacts")
public final class Contacts {

    @JacksonXmlElementWrapper(localName = "contact",useWrapping = false)
    private List<Contact> contact;

    public Contacts() {
    }

    public Contacts(List<Contact> contact) {
        this.contact = contact;
    }


    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "contact=" + contact +
                '}';
    }
}
