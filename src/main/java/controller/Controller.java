package controller;

import models.Contact;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *Интерфейс для контроллера
 */
public interface Controller {

    /**
     *Обновление контакта
     */
    void updateContact(List<String> attContact) throws ParserConfigurationException, SAXException, IOException, TransformerException;

    /**
     *Удаление контакта
     */
    void removeContact(String fio) throws SAXException, ParserConfigurationException, IOException, TransformerException;

    /**
     *Добавить контакту группу
     */
    void appGroupContact(List<String> attContact) throws SAXException, ParserConfigurationException, IOException, TransformerException;

    /**
     *Удаление группы у контакта контакта
     */
    void removeGroupContact(String fio) throws IOException, SAXException, ParserConfigurationException, TransformerException;

    /**
     *Список контактов
     */
    Set<Contact> getContacts() throws ParserConfigurationException, SAXException,
            XPathExpressionException, IOException;

    /**
     *Список контактов опр. группы
     */
    Set<String> getContactsGroup(String name) throws ParserConfigurationException, SAXException,
            XPathExpressionException, IOException;

    /**
     *Список групп
     */
    Set<String> getGroups() throws XPathExpressionException, ParserConfigurationException, SAXException, TransformerConfigurationException, IOException;

    /**
     *Удаление группы
     */
    void removeGroup(String name) throws ParserConfigurationException, SAXException, XPathExpressionException, TransformerException, IOException;

    /**
     *Обновление  группы
     */
    void updateGroup(List<String> attGoup) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    /**
     * Создание сущности
     */
    void addEntity (List<String> attrEntity,String command) throws IOException, TransformerException,
            ParserConfigurationException, SAXException;

    /**
     * Возвращает контакт
     */
    Contact getContact(String fio) throws ParserConfigurationException, SAXException, IOException;

    /**
     * Проверяет контакт на сущ.
     */
    boolean existContact(String name) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    /**
     * Проверяет группу на сущ.
     */
    boolean existGroup(String name);

    /**
     * Поиск по имени
     */
    String searchName(String fio) throws ParserConfigurationException, SAXException, IOException;

}
