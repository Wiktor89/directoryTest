package service;

import dao.DirectoryDaoImpl;
import models.Contact;
import models.Entity;
import models.Group;
import storage.RefBook;
import views.ViewImpl;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

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
    private ViewImpl view = new ViewImpl();
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
    public void listGroupContact() {
        String nameGroup = view.getEntGroup();
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            Group group = contact.getGroup();
            if (group.getName().equalsIgnoreCase(nameGroup)){
                System.out.println(contact.informationContact());
            }
        }
    }

    @Override
    public void listGroup() throws IOException {
        Set<Group> groups = refBook.getGroups();
        if (!groups.isEmpty()){
            for (Group group : groups){
                System.out.println(group);
            }
        }else {
            throw  new IOException();
        }

    }

    @Override
    public void addGroup(Entity entity) throws IOException {
        Group group = (Group) entity;
        Set<Group> groups = refBook.getGroups();
        String name = view.getEntGroup();
        if (name.trim().length() > 0){
            group.setName(name);
            groups.add(group);
            view.getSuc();
        }else {
            throw new IOException();
        }
        refBook.setId(group.getId());
        this.dao.save(refBook);
    }

    @Override
    public void remGroup() throws IOException {
        Set<Contact> contacts = refBook.getContacts();
        Set<Group> groups = refBook.getGroups();
        if (!groups.isEmpty()){
            for (Group group : groups){
                view.getListGroup(group);
            }
        }else {
            throw  new IOException();
        }
        String nameGroup = view.getEntGroup();
        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext()){
            Group group = iterator.next();
            if (group.getName().equalsIgnoreCase(nameGroup)){
                iterator.remove();
                view.getSuc();
                for (Contact contact : contacts){
                    group = contact.getGroup();
                    if (group.getName().equalsIgnoreCase(nameGroup)){
                        group.setName(view.getNoGroup());
                    }
                }
            }
        }
        this.dao.save(refBook);
    }

    @Override
    public void updGroup() throws IOException {
        String nameGroup = view.getEntGroup();
        Set<Contact> contacts = refBook.getContacts();
        Set<Group> groups = refBook.getGroups();
        for (Group group : groups){
            if (group.getName().equalsIgnoreCase(nameGroup)){
                String nameNewGroup = view.getEntGroup();
                if (nameNewGroup.trim().length() > 0){
                    group.setName(nameNewGroup);
                    for (Contact contact : contacts){
                        group = contact.getGroup();
                        group.setName(group.getName());
                }
                }else {
                    throw new IOException();
                }
            }
        }
        this.dao.save(refBook);
    }

    @Override
    public boolean existGroups(String nameGroup){
        Set<Group> groups = refBook.getGroups();
        boolean result = false;
        for (Group group : groups){
            if (group.getName().equalsIgnoreCase(nameGroup)){
                result = true;
            }
        }
        return result;
    }
}
