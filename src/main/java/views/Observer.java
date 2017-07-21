package views;

import models.Contact;
import models.Group;

import java.util.Set;

/**
 *Наблюдатель за спискоми контактов и групп
 */
public interface Observer {

    void handle (Set<Contact> contacts, Set<Group> groups);

}
