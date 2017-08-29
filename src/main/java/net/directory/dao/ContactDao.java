package net.directory.dao;

import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 */
public interface ContactDao {

    /**
     *Добавление контакта
     */
    void addContact (Entity entity) throws SQLException;

    /**
     *Обновление контакта
     * @param attContact
     */
    boolean updateContact(List<String> attContact) throws SQLException;

    /**
     *Удаление контакта
     * @param id
     */
    boolean removeContact(Integer id) throws SQLException;

    /**
     *Добавить контакту группу
     */
    boolean appGroupContact(List<String> attContact) throws SQLException;

    /**
     *Удаление группы у контакта контакта
     * @param attr
     */
    boolean removeGroupContact(List<String> attr) throws SQLException;

    /**
     *Список контактов
     */
    Set<Contact> getContacts() throws SQLException;

    /**
     *Проверка контакта на существование
     */
    boolean existContact(String name) throws SQLException;

    /**
     *Возвращает контакт
     */
    Contact getContact(String fio) throws SQLException;

    /**
     *Поиск контакта по имени
     */
    Contact searchName (String fio) throws SQLException;
    
    /**
     * Страница авторизации
     * @param attr
     */
    User authorizationPage(List<String> attr) throws SQLException;
    
    /**
     *Возвращает контакт
     */
    Contact getContact(Integer id) throws SQLException;


}
