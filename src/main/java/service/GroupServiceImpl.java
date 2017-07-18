package service;

import dao.DirectoryDaoImpl;
import models.Contact;
import models.Group;
import storage.RefBook;
import utilits.ConsoleReader;
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
    private ConsoleReader consol = null;
    private RefBook refBook = null;
    private DirectoryDaoImpl dao = new DirectoryDaoImpl();

    public GroupServiceImpl(RefBook refBook,ConsoleReader reader) {
        this.refBook = refBook;
        this.consol = reader;
    }

    public DirectoryDaoImpl getDao() {
        return dao;
    }
    public void setDao(DirectoryDaoImpl dao) {
        this.dao = dao;
    }
    public ConsoleReader getConsol() {
        return consol;
    }
    public void setConsol(ConsoleReader consol) {
        this.consol = consol;
    }
    public RefBook getRefBook() {
        return refBook ;
    }
    public void setRefBook(RefBook refBook) {
        this.refBook = refBook;
    }

    @Override
    public void listGroupContact(String nameGroup) {
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
    public void addGroup(String nameGroup) throws IOException {
        Set<Group> groups = refBook.getGroups();
        Group group = null;
        if (nameGroup.trim().length() > 0){
            group = new Group(nameGroup);
            groups.add(group);
            System.out.println("Группа успешно добавлена");
        }else {
            throw new IOException();
        }
        refBook.setId(group.getId());
        this.dao.save(refBook);
    }

    @Override
    public void removeGroup(String nameGroup) {
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
                        group.setName("нет группы");
                    }
                }
            }
        }
        this.dao.save(refBook);
    }

    @Override
    public void updateGroup(String nameGroup) throws IOException {
        Set<Contact> contacts = refBook.getContacts();
        Set<Group> groups = refBook.getGroups();
        for (Group group : groups){
            if (group.getName().equalsIgnoreCase(nameGroup)){
                System.out.println("Введите имя новой группы");
                String nameNewGroup = this.consol.readString();
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
