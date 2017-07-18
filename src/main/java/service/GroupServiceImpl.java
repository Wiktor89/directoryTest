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
    private Set<Contact> contacts = null;
    private Set<Group> groups = null;
    private DirectoryDaoImpl dao = new DirectoryDaoImpl();

    public GroupServiceImpl(RefBook refBook,ConsoleReader reader) {
        this.refBook = refBook;
        this.contacts = this.refBook.getContacts();
        this.groups = this.refBook.getGroups();
        this.consol = reader;
    }

    public DirectoryDaoImpl getDao() {
        return dao;
    }
    public void setDao(DirectoryDaoImpl dao) {
        this.dao = dao;
    }
    public Set<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
    public Set<Group> getGroups() {
        return groups;
    }
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
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

    /**
     *Список контактов опр. группы
     */
    @Override
    public void listGroupContact(String nameGroup) {
        for (Contact contact : this.contacts){
            Group group = contact.getGroup();
            if (group.getNameGroup().equalsIgnoreCase(nameGroup)){
                System.out.println(contact.informationContact());
            }
        }
    }

    /**
     *Список групп
     */
    @Override
    public void listGroup() throws Exception {
        if (!this.groups.isEmpty()){
            for (Group group : this.groups){
                System.out.println(group.getNameGroup());
            }
        }else {
            throw  new Exception();
        }

    }

    /**
     *Добавление группы
     */
    @Override
    public void addGroup(String nameGroup) throws IOException {
        if (nameGroup.trim().length() > 0){
            groups.add(new Group(nameGroup));
            System.out.println("Группа успешно добавлена");
        }else {
            throw new IOException();
        }
        this.dao.save(refBook);
    }

    /**
     *Удаление группы
     */
    @Override
    public void removeGroup(String nameGroup) {
        Iterator<Group> iterator = this.groups.iterator();
        while (iterator.hasNext()){
            Group group = iterator.next();
            if (group.getNameGroup().equalsIgnoreCase(nameGroup)){
                iterator.remove();
                for (Contact contact : this.contacts){
                    group = contact.getGroup();
                    if (group.getNameGroup().equalsIgnoreCase(nameGroup)){
                        group.setNameGroup("нет группы");
                    }
                }
            }
        }
        this.dao.save(refBook);
    }

    /**
     *Обновление  группы
     */
    @Override
    public void updateGroup(String nameGroup) throws IOException {
        for (Group group : this.groups){
            if (group.getNameGroup().equalsIgnoreCase(nameGroup)){
                System.out.println("Введите имя новой группы");
                String nameNewGroup = this.consol.readString();
                if (nameNewGroup.trim().length() > 0){
                    group.setNameGroup(nameNewGroup);
                    for (Contact contact : this.contacts){
                        group = contact.getGroup();
                        group.setNameGroup(group.getNameGroup());
                }
                }else {
                    throw new IOException();
                }
            }
        }
        this.dao.save(refBook);
    }

    /**
     *Смотрим есть группа в списке
     */
    @Override
    public boolean existGroups(String nameGroup){
        boolean result = false;
        for (Group group : this.groups){
            if (group.getNameGroup().equalsIgnoreCase(nameGroup)){
                result = true;
            }
        }
        return result;
    }


}
