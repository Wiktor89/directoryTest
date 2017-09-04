package net.directory.service;

import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Интерфейс для контактов
 */
public interface ContactService {

    /**
     *Добавление контакта
     */
    void addContact (Entity entity) throws  SQLException;

    /**
     *Обновление контакта
     * @param attContact
     */
    void updateContact(List<String> attContact) throws IOException, SQLException;

    /**
     *Удаление контакта
     * @param id
     */
    void removeContact(Integer id) throws  SQLException;

    /**
     *Добавить контакту группу
     */
    void appGroupContact(List<String> attContact) throws   SQLException;

    /**
     *Удаление группы у контакта контакта
     * @param attr
     */
    void removeGroupContact(List<String> attr) throws  IOException,  SQLException;

    /**
     *Список контактов
     */
    Set<Contact> getContacts() throws  IOException, SQLException;

    /**
     *Проверка контакта на существование
     */
    boolean existContact(String name) throws  IOException, SQLException;

    /**
     *Возвращает контакт
     */
    Contact getContact(String fio) throws IOException,  SQLException;

    /**
     *Поиск контакта по имени
     */
    Contact searchName (String fio) throws IOException,  SQLException;
    
    /**
     * Страница авторизации
     * @param attr
     */
    User authorizationPage(List<String> attr) throws SQLException;


}
