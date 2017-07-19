package views;

import models.Contact;
import models.Group;

import java.util.List;

/**
 * Интерфейс для вью
 */
public interface View {

    /**
     * Стартовая страница
     */
    void startPage();

    /**
     * Страница для действий с контактом
     */
     void pageActionContact();

    /**
     * Страница для действий с группой
     */
     void pageActionGroup();

    /**
     * Действия с контакта
     */
    void actionContacts();

    /**
     * Действия с группой
     */
    void actionGroup();

    /**
     * Добавление контакта
     */
    List<String> addContact();

    /**
     * Действия для группы
     */
    List<String> updateContact();

    /**
     * Список контактов
     */
    void listContacts(Contact contact);

    /**
     * Список групп
     */
    void listGroup (Group group);

    /**
     * Полная инф. о контакте
     */
    void informationContact(Contact contact);

    /**
     * Ввод контакта
     */
    String entContact();

    /**
     * Ввод группы
     */
    String entGroup();

    /**
     * Нет групп
     */
    String noGroup();

}
