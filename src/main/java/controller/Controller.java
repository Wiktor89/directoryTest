package controller;

import models.Contact;
import models.Group;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *Интерфейс для контроллера
 */
public interface Controller {

    /**
     *Обновление контакта
     */
    void updContact(Contact contact);

    /**
     *Удаление контакта
     */
    void remContact(String fio);

    /**
     *Добавить контакту группу
     */
    void appGroupContact(Contact contact);

    /**
     *Удаление группы у контакта контакта
     */
    void remGroupContact(Contact contact);

    /**
     *Список контактов
     */
    Set<Contact> getContacts();

    /**
     *Список контактов опр. группы
     */
    List<Contact> getContactsGroup(String name);

    /**
     *Список групп
     */
    Set<Group> getGroups();

    /**
     *Удаление группы
     */
    void remGroup(String name) throws IOException;

    /**
     *Обновление  группы
     */
    void updGroup(Group group,String name) throws IOException;

    /**
     * Создание сущности
     */
    void addEntity (List<String> attrEntity,String command) throws IOException;
}
