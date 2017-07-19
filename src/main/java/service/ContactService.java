package service;

import models.Entity;

import java.io.IOException;

/**
 * Интерфейс для контактов
 */
public interface ContactService {

    /**
     *Добавление контакта
     */
    void addContact (Entity entity) throws IOException;

    /**
     *Обновление контакта
     */
    void updateContact(String fioContact) throws IOException;

    /**
     *Удаление контакта
     */
    void removeContact();

    /**
     *Добавить контакту группу
     */
    void appGroupContact(String fioContact) throws Exception;

    /**
     *Удаление группы у контакта контакта
     */
    void removeGroupContact(String nameContact);

    /**
     *Инф. по контакту
     */
    void informationContact (String fioContact);

    /**
     *Список контактов
     */
    void listContacts();

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroups(String nameGroup);
}
