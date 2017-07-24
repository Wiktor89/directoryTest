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
    void updContact(List<String> attContact);

    /**
     *Удаление контакта
     */
    void remContact(String fio);

    /**
     *Добавить контакту группу
     */
    void appGroupContact(List<String> attContact);

    /**
     *Удаление группы у контакта контакта
     */
    void remGroupContact(String fio);

    /**
     *Список контактов
     */
    Set<Contact> getContacts();

    /**
     *Список контактов опр. группы
     */
    Set<Contact> getContactsGroup(String name);

    /**
     *Список групп
     */
    Set<Group> getGroups();

    /**
     *Удаление группы
     */
    void remGroup(String name) ;

    /**
     *Обновление  группы
     */
    void updGroup(List<String> attGoup) ;

    /**
     * Создание сущности
     */
    void addEntity (List<String> attrEntity,String command) throws IOException;

    /**
     * Возвращает контакт
     */
    Contact getContact(String fio);

    /**
     * Проверяет контакт на сущ.
     */
    boolean existContact(String name);

    /**
     * Проверяет группу на сущ.
     */
    boolean existGroup(String name);

}
