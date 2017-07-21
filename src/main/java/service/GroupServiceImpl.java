package service;

import dao.DirectoryDaoImpl;
import models.Contact;
import models.Entity;
import models.Group;
import sorted.ContactFioComparator;
import storage.RefBook;
import views.ViewImpl;

import java.io.IOException;
import java.util.*;

/**
 *Сервис для контакта
 */
public class GroupServiceImpl implements GroupService {

    /**
     *consol
     * refBook
     * contacts
     * groups
     * dao
     */
    private RefBook refBook = null;
    private DirectoryDaoImpl dao = new DirectoryDaoImpl();

    public GroupServiceImpl(RefBook refBook) {
        this.refBook = refBook;
    }

    public RefBook getRefBook() {
        return refBook ;
    }
    public void setRefBook(RefBook refBook) {
        this.refBook = refBook;
    }

    @Override
    public Set<Contact> getContactsGroup(String name) {
        Set<Contact> contacts1 = new TreeSet<>(new ContactFioComparator());
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            Group group = contact.getGroup();
            if (group.getName().equalsIgnoreCase(name)){
                contacts1.add(contact);
            }
        }
        return contacts1;
    }

    @Override
    public Set<Group> getGroups(){
        Set<Group> groups = refBook.getGroups();
        return groups;
    }

    @Override
    public void addGroup(Entity entity)  {
        Group group = (Group) entity;
        Set<Group> groups = refBook.getGroups();
        groups.add(group);
        refBook.setId(group.getId());
        this.dao.save(refBook);
    }

    @Override
    public void remGroup(String name)  {
        Set<Group> groups = refBook.getGroups();
        Set<Contact> contacts = refBook.getContacts();
        if (!groups.isEmpty()){
                Iterator<Group> iterator = groups.iterator();
                while (iterator.hasNext()){
                    Group group = iterator.next();
                    if (group.getName().equalsIgnoreCase(name)){
                        iterator.remove();
                    }
                    for (Contact contact : contacts){
                        Group group1 = contact.getGroup();
                        if (group1.getName().equalsIgnoreCase(name)){
                            group1.setName("нет группы");
                        }
                    }
                }
        }
        this.dao.save(refBook);
    }

    @Override
    public void updGroup(Group group,String oldName) {
        Set<Group> groups = refBook.getGroups();
        groups.add(group);
        String newName = group.getName();
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            Group group1 = contact.getGroup();
            if (group1.getName().equalsIgnoreCase(oldName)){
                group1.setName(newName);
            }
        }
        this.dao.save(refBook);
    }

    @Override
    public boolean existGroup(String name){
        boolean result = false;
        Set<Group> groups = refBook.getGroups();
        for (Group group : groups){
            if (group.getName().equalsIgnoreCase(name)){
                result = true;
            }
        }
        return result;
    }// проверяет на существование группы

    public Group getGroup (String name){
        Group group1 = null;
        Set<Group> groups = refBook.getGroups();
        for (Group group : groups){
            if (group.getName().equalsIgnoreCase(name)){
                group1 = group;
            }
        }
        return  group1;
    }// Возвращает группу
}
