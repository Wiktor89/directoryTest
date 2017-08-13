package dao;

import models.Contact;
import models.Entity;
import models.Group;
import models.User;

import java.sql.SQLException;
import java.util.List;
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
     */
    boolean removeGroup(String name) throws SQLException;

    /**
     *Обновление  группы
     * @param attGroup
     */
    boolean updateGroup(List<String> attGroup) throws SQLException;

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroup(String name) throws SQLException;

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
