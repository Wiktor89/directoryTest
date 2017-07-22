package views;

import java.io.IOException;

/**
 * Интерфейс для вью
 */
public interface View {

//    /**
//     * Стартовая страница
//     */
//    void startPage();

//    /**
//     * Страница для действий с контактом
//     */
//     void pageActionContact();
//
//    /**
//     * Страница для действий с группой
//     */
//     void pageActionGroup();

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
    void updContact() ;

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

//    /**
//     * Нет групп
//     */
//    void getNoGroup();

//    /**
//     * Контакт успешно добавлен
//     */
//    void getSuc();
}
