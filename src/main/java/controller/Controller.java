package controller;

import models.Contact;
import models.Group;
import models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *Интерфейс для контроллера
 */
public interface Controller {

    /**
     *Обновление контакта
     */
    void updateContact(List<String> attContact) throws IOException, SQLException;

    /**
     *Удаление контакта
     * @param id
     */
    void removeContact(Integer id) throws  IOException, SQLException;

    /**
     *Добавить контакту группу
     */
    void appGroupContact(List<String> attContact) throws  IOException,  SQLException;

    /**
     *Удаление группы у контакта контакта
     * @param fio
     */
    void removeGroupContact(List<String> fio) throws SQLException;

    /**
     *Список контактов
     */
    Set<Contact> getContacts() throws IOException, SQLException;

    /**
     *Список контактов опр. группы
     */
    Set<Contact> getContactsGroup(String name) throws IOException, SQLException;

    /**
     *Список групп
     */
    Set<Group> getGroups();

    /**
     *Удаление группы
     */
    boolean removeGroup(String name) throws  IOException, SQLException;

    /**
     *Обновление  группы
     */
    boolean updateGroup(List<String> attGroup) throws  IOException, SQLException;

    /**
     * Создание сущности
     */
    void addEntity (List<String> attrEntity,String command) throws IOException,  SQLException;

    /**
     * Возвращает контакт
     */
    Contact getContact(String fio) throws  IOException, SQLException;

    /**
     * Проверяет контакт на сущ.
     */
    boolean existContact(String name) throws  IOException, SQLException;

    /**
     * Проверяет группу на сущ.
     */
    boolean existGroup(String name) throws IOException, SQLException;
	
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
	
	/**
	 * Страница авторизации
	 * @param attr
	 */
	User authorizationPage(List<String> attr) throws SQLException;


}
