package views;

import dao.ContactDao;
import models.Contact;
import models.Group;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.sql.SQLException;
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
        if (o instanceof ContactDao) {
            Set<Contact> contacts = null;
            try {
                contacts = ((ContactDao) o).getContacts();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (!contacts.isEmpty()) {
                System.out.println("******************************");
                for (Contact contact : contacts) {
                    System.out.println(contact.contactInf());
                }
                System.out.println("******************************");
            } else {
                System.out.println("список контактов пуст");
            }
        }
    }
}
