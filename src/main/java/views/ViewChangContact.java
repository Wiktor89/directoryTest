package views;

import dao.DomSaxContactsParser;
import dao.DomSaxGroupParser;
import models.Contact;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 *Доп. вью для демонстрации Observer
 */
public class ViewChangContact implements Observer {

    private static ViewChangContact viewChangContact;

    public static ViewChangContact getViewChangContact(){
        if (viewChangContact == null){
            viewChangContact = new ViewChangContact();
        }
        return viewChangContact;
    }

    private ViewChangContact() {
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof DomSaxContactsParser) {
            Set<Contact> contacts = null;
            try {
                contacts = ((DomSaxContactsParser) o).getContacts();
            } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
                e.printStackTrace();
            }
            if (!contacts.isEmpty()) {
                System.out.println("******************************");
                for (Contact contact : contacts) {
                    System.out.println(contact);
                }
                System.out.println("******************************");
            } else {
                System.out.println("список контактов пуст");
            }
        }
    }
}
