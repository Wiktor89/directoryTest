package controller;

import java.io.IOException;

/**
 *Интерфейс для контроллера
 */
public interface Controller {

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
     *Список контактов опр. группы
     */
    void listGroupContact();

    /**
     *Список групп
     */
    void listGroup() throws IOException;

    /**
     *Удаление группы
     */
    void removeGroup();

    /**
     *Обновление  группы
     */
    void updateGroup() throws IOException;

    /**
     * Создание сущности
     */
    void addEntity (String command) throws IOException;
}
