package service;

import jdk.internal.org.xml.sax.SAXException;
import models.Contact;
import models.Entity;
import models.User;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
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
    void addContact (Entity entity) throws IOException, ParserConfigurationException, SAXException, TransformerException, SQLException;

    /**
     *Обновление контакта
     * @param attContact
     */
    void updateContact(List<String> attContact) throws IOException, SAXException, ParserConfigurationException, TransformerException, SQLException;

    /**
     *Удаление контакта
     */
    void removeContact(String fio) throws SAXException, TransformerException, ParserConfigurationException, IOException, SQLException;

    /**
     *Добавить контакту группу
     */
    void appGroupContact(List<String> attContact) throws SAXException, TransformerException, ParserConfigurationException, IOException, SQLException;

    /**
     *Удаление группы у контакта контакта
     * @param attr
     */
    void removeGroupContact(List<String> attr) throws ParserConfigurationException, SAXException, IOException, TransformerException, SQLException;

    /**
     *Список контактов
     */
    Set<Contact> getContacts() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, SQLException;

    /**
     *Проверка контакта на существование
     */
    boolean existContact(String name) throws ParserConfigurationException, TransformerException, SAXException, IOException, SQLException;

    /**
     *Возвращает контакт
     */
    Contact getContact(String fio) throws IOException, SAXException, ParserConfigurationException, SQLException;

    /**
     *Поиск контакта по имени
     */
    Contact searchName (String fio) throws IOException, SAXException, ParserConfigurationException, SQLException;
    
    /**
     * Страница авторизации
     * @param attr
     */
    User authorizationPage(List<String> attr) throws SQLException;


}
