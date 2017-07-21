package views;

import models.Contact;
import models.Group;

import java.io.Serializable;
import java.util.Set;

/**
 *
 */
public class ViewChangModel implements Observer,Serializable {

    @Override
    public void handle(Set<Contact> contacts, Set<Group> groups) {
        System.out.println("********************************");
        for (Contact contact : contacts){
            System.out.println(contact.contactInf());
        }
        System.out.println("********************************");
        for (Group group : groups){
            System.out.println(group.informationGroup());
        }
        System.out.println("********************************");
    }
}
