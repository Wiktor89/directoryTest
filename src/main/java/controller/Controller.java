package controller;

/**
 *Описание, что контроллер должен уметь делать
 * добавлять, обновлять, удалять,добавлять группу контакту,удалять группу у контакта,
 * инф. о контакте, список контактов
 */
public interface Controller {

    /**
     * Все что к контакту
     */
    void addContact ();
    void updateContact();
    void removeContact();
    void appGroupContact();
    void removeGroupContact();
    void informationContact ();
    void listContacts();

    /**
     * Все что к группам
     */
    void listGroupContact();
    void listGroup();
    void addGroup();
    void removeGroup();
    void updateGroup();

    /**
     * Page and actions
     */
    void startPage();
    void actionContacts();
    void actionGroup();
}
