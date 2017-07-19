package service;

import dao.DirectoryDaoImpl;
import models.Contact;
import models.Entity;
import models.Group;
import storage.RefBook;
import utilits.ConsoleReader;
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
    private DirectoryDaoImpl dao = new DirectoryDaoImpl();
    private ViewImpl view = new ViewImpl();

    public GroupServiceImpl(RefBook refBook) {
        this.refBook = refBook;
    }

    public DirectoryDaoImpl getDao() {
        return dao;
    }
    public void setDao(DirectoryDaoImpl dao) {
        this.dao = dao;
    }
    public RefBook getRefBook() {
        return refBook ;
    }
    public void setRefBook(RefBook refBook) {
        this.refBook = refBook;
    }

    @Override
    public void listGroupContact() {
        String nameGroup = view.entGroup();
        Set<Contact> contacts = refBook.getContacts();
        for (Contact contact : contacts){
            Group group = contact.getGroup();
            if (group.getName().equalsIgnoreCase(nameGroup)){
                System.out.println(contact.informationContact());
            }
        }
    }

    @Override
    public void listGroup() throws Exception {
        Set<Group> groups = refBook.getGroups();
        if (!groups.isEmpty()){
            for (Group group : groups){
                System.out.println(group);
            }
        }else {
            throw  new Exception();
        }

    }

    @Override
    public void addGroup(Entity entity) throws IOException {
        Group group = (Group) entity;
        Set<Group> groups = refBook.getGroups();
        String name = view.entGroup();
        if (name.trim().length() > 0){
            group = new Group(name);
            groups.add(group);
            System.out.println("Группа успешно добавлена");
        }else {
            throw new IOException();
        }
        refBook.setId(group.getId());
        this.dao.save(refBook);
    }

    @Override
    public void removeGroup() {
        String nameGroup = view.entGroup();
        Set<Contact> contacts = refBook.getContacts();
        Set<Group> groups = refBook.getGroups();
        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext()){
            Group group = iterator.next();
            if (group.getName().equalsIgnoreCase(nameGroup)){
                iterator.remove();
                for (Contact contact : contacts){
                    group = contact.getGroup();
                    if (group.getName().equalsIgnoreCase(nameGroup)){
                        group.setName(view.noGroup());
                    }
                }
            }
        }
        this.dao.save(refBook);
    }

    @Override
    public void updateGroup() throws IOException {
        String nameGroup = view.entGroup();
        Set<Contact> contacts = refBook.getContacts();
        Set<Group> groups = refBook.getGroups();
        for (Group group : groups){
            if (group.getName().equalsIgnoreCase(nameGroup)){
                String nameNewGroup = view.entGroup();
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
