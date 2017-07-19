package controller;

/**
 *Интерфейс для контроллера
 */
public interface Controller {

    /**
     *Обновление контакта
     */
    void updateContact();

    /**
     *Удаление контакта
     */
    void removeContact();

    /**
     *Добавить контакту группу
     */
    void appGroupContact();

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
    void listGroup();

    /**
     *Удаление группы
     */
    void removeGroup();

    /**
     *Обновление  группы
     */
    void updateGroup();

    /**
     * Создание сущности
     */
    void addEntity (String command);
}
