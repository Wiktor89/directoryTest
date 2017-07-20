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
    void updateContact() throws IOException;

    /**
     *Удаление контакта
     */
    void removeContact();

    /**
     *Добавить контакту группу
     */
    void appGroupContact() throws IOException;

    /**
     *Удаление группы у контакта контакта
     */
    void removeGroupContact();

    /**
     *Инф. по контакту
     */
    void informationContact ();

    /**
     *Список контактов
     */
    void listContacts();

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroups(String nameGroup);
}
