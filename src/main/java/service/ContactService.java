package service;

import models.Contact;
import models.Entity;

import java.util.List;
import java.util.Set;

/**
 * Интерфейс для контактов
 */
public interface ContactService {

    /**
     *Добавление контакта
     */
    void addContact (List<String> attrEntity, Entity entity);

    /**
     *Обновление контакта
     * @param attContact
     */
    void updContact(List<String> attContact);

    /**
     *Удаление контакта
     */
    void remContact(String fio);

    /**
     *Добавить контакту группу
     */
    void appGroupContact(List<String> attContact);

    /**
     *Удаление группы у контакта контакта
     */
    void remGroupContact(String fio);

    /**
     *Список контактов
     */
    Set<Contact> getContacts();

    /**
     *Проверка контакта на существование
     */
    boolean existContact(String name);
}
