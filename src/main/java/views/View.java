package views;

import java.io.IOException;

/**
 * Интерфейс для вью
 */
public interface View {

    /**
     * Действия с контакта
     */
    void actionContact() throws IOException ;

    /**
     * Действия с группой
     */
    void actionGroup() ;

    /**
     * Добавление контакта
     */
    void addContact(String command) throws IOException;

    /**
     * Действия для группы
     */
    void updateContact() ;

    /**
     * Список контактов
     */
    void getContacts();

    /**
     * Список групп
     */
    void getGroups();

    /**
     * Полная инф. о контакте
     */
    void getContactInfo() ;

    /**
     * Ввод контакта
     */
    String getNameContact() ;

    /**
     * Ввод группы
     */
    String getNameGroup();

    /**
     * Поиск контакта по имени
     */
    void searchName();

}
