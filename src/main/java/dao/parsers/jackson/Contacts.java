package dao.parsers.jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

/**
 *
 */
@JacksonXmlRootElement(localName = "contacts")
public final class Contacts {

    @JacksonXmlElementWrapper(localName = "contact",useWrapping = false)
    private List<ContactJac> contact;

    public Contacts() {
    }

    public Contacts(List<ContactJac> contact) {
        this.contact = contact;
    }


    public List<ContactJac> getContact() {
        return contact;
    }

    public void setContact(List<ContactJac> contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "contact=" + contact +
                '}';
    }
}
