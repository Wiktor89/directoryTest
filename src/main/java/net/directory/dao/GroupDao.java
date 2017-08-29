package net.directory.dao;

import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.Group;
import net.directory.models.User;

import java.sql.SQLException;
import java.util.Set;

/**
 *
 */
public interface GroupDao {

    /**
     *Добавление группы
     */
    void addGroup(Entity entity) throws SQLException;

    /**
     *Удаление группы
     * @param id
     */
    boolean removeGroup(Integer id) throws SQLException;

    /**
     *Обновление  группы
     * @param id
     * @param name
     */
    boolean updateGroup(Integer id, String name) throws SQLException;

    /**
     *Смотрим есть группа в списке
     * @param id
     */
    boolean existGroup(Integer id) throws SQLException;

    /**
     *Список групп
     */
    Set<Group> getGroups();

    /**
     *Список контактов опр. группы
     */
    Set<Contact> getContactsGroup(String name) throws SQLException;
    
    /**
     * Количество пользователей
     */
    Integer numberUsers() throws SQLException;
    
    /**
     * Количество контактов каждого пользователя
     * @param name
     */
    Integer numberContacts(String name) throws SQLException;
    
    /**
     * Количество групп каждого пользователя
     * @param name
     */
    Integer quantityGroupsUser(String name) throws SQLException;
    
    /**
     * Среднее количество контактов в группах
     */
    Integer averageNumberContactsGroups() throws SQLException;
    
    /**
     * Среднее количество контактов у пользователя
     */
    Integer averageNumberContactsUser() throws SQLException;
    
    /**
     * Пользователь с количеством контактов < 10
     */
    Set<User> userWithContactsMin_10() throws SQLException;


}
