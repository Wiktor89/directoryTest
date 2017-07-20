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
    void updContact() throws IOException;

    /**
     *Удаление контакта
     */
    void remContact();

    /**
     *Добавить контакту группу
     */
    void appGroupContact() throws IOException;

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
    void listContacts();

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroups(String nameGroup);
}
