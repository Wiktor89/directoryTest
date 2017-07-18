package controller;

/**
 *Интерфейс для контроллера
 */
public interface Controller {

    /**
     *Добавление контакта
     */
    void addContact ();

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
     *Добавление группы
     */
    void addGroup();

    /**
     *Удаление группы
     */
    void removeGroup();

    /**
     *Обновление  группы
     */
    void updateGroup();

    /**
     * Стартовая страница
     */
    void startPage();

    /**
     * Действия для контакта
     */
    void actionContacts();

    /**
     * Действия для группы
     */
    void actionGroup();
}
