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
    void remGroupContact();

    /**
     *Инф. по контакту
     */
    void contactInf();

    /**
     *Список контактов
     */
    Set<Contact> getContacts();

    /**
     *Список контактов опр. группы
     */
    void listGroupContact();

    /**
     *Список групп
     */
    Set<Group> getGroups();

    /**
     *Удаление группы
     */
    void remGroup() throws IOException;

    /**
     *Обновление  группы
     */
    void updGroup() throws IOException;

    /**
     * Создание сущности
     */
    void addEntity (List<String> attrEntity,String command) throws IOException;
}
