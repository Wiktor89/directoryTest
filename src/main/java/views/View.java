package views;

import models.Contact;
import models.Group;

import java.io.IOException;
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
    void actionContact() throws IOException;

    /**
     * Действия с группой
     */
    void actionGroup() throws IOException;

    /**
     * Добавление контакта
     */
    void addContact(String command) throws IOException;

    /**
     * Действия для группы
     */
    void updContact();

    /**
     * Список контактов
     */
    void getContacts();

    /**
     * Список групп
     */
    void getListGroup(Group group);

    /**
     * Полная инф. о контакте
     */
    void getContactInfo(Contact contact);

    /**
     * Ввод контакта
     */
    String getEntContact();

    /**
     * Ввод группы
     */
    String getEntGroup();

    /**
     * Нет групп
     */
    String getNoGroup();

    /**
     * Контакт успешно добавлен
     */
    void getSuc();
}
