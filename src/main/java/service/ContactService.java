package service;

import java.io.IOException;

/**
 * Интерфейс для контактов
 */
public interface ContactService {

    /**
     * Все что к контакту
     */
    void addContact () throws IOException;
    void updateContact(String fioContact);
    void removeContact();
    void appGroupContact(String fioContact);
    void removeGroupContact(String nameContact);
    void informationContact (String fioContact);
    void listContacts();
    boolean existGroups(String nameGroup);
}
