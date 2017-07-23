package views;

import models.Contact;
import models.Group;
import storage.RefBook;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 *
 */
public class ViewChangModel implements Observer,Serializable {

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof RefBook){
            Set<Contact> contacts = ((RefBook) o).getContacts();
            if (!contacts.isEmpty()){
                System.out.println("******************************");
                for (Contact contact : contacts){
                    System.out.println(contact);
                }
                System.out.println("******************************");
            }else {
                System.out.println("список контактов пуст");
            }
            Set<Group> groups  = ((RefBook) o).getGroups();
            if (!groups.isEmpty()){
                System.out.println("******************************");
                for (Group group : groups){
                    System.out.println(group);
                }
                System.out.println("******************************");
            }else {
                System.out.println("список групп пуст");

            }
        }
    }
}
