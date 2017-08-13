package controller;

import jdk.internal.org.xml.sax.SAXException;
import models.Contact;
import models.Group;
import models.User;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
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
    void updateContact(List<String> attContact) throws ParserConfigurationException, SAXException, IOException, TransformerException, SQLException;

    /**
     *Удаление контакта
     */
    void removeContact(String fio) throws SAXException, ParserConfigurationException, IOException, TransformerException, SQLException;

    /**
     *Добавить контакту группу
     */
    void appGroupContact(List<String> attContact) throws SAXException, ParserConfigurationException, IOException, TransformerException, SQLException;

    /**
     *Удаление группы у контакта контакта
     * @param fio
     */
    void removeGroupContact(List<String> fio) throws SQLException;

    /**
     *Список контактов
     */
    Set<Contact> getContacts() throws ParserConfigurationException, SAXException,
		    XPathExpressionException, IOException, SQLException;

    /**
     *Список контактов опр. группы
     */
    Set<Contact> getContactsGroup(String name) throws ParserConfigurationException, SAXException,
		    XPathExpressionException, IOException, SQLException;

    /**
     *Список групп
     */
    Set<Group> getGroups();

    /**
     *Удаление группы
     */
    boolean removeGroup(String name) throws ParserConfigurationException, SAXException,
		    XPathExpressionException, TransformerException, IOException, SQLException;

    /**
     *Обновление  группы
     */
    boolean updateGroup(List<String> attGoup) throws ParserConfigurationException, IOException,
		    SAXException, TransformerException, SQLException;

    /**
     * Создание сущности
     */
    void addEntity (List<String> attrEntity,String command) throws IOException, TransformerException,
		    ParserConfigurationException, SAXException, SQLException;

    /**
     * Возвращает контакт
     */
    Contact getContact(String fio) throws ParserConfigurationException,
		    SAXException, IOException, SQLException;

    /**
     * Проверяет контакт на сущ.
     */
    boolean existContact(String name) throws ParserConfigurationException, IOException,
		    SAXException, TransformerException, SQLException;

    /**
     * Проверяет группу на сущ.
     */
    boolean existGroup(String name) throws ParserConfigurationException, SAXException,
		    IOException, XPathExpressionException, SQLException;
	
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
