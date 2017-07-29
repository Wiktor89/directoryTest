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
    void updateContact(List<String> attContact);

    /**
     *Удаление контакта
     */
    void removeContact(String fio);

    /**
     *Добавить контакту группу
     */
    void appGroupContact(List<String> attContact);

    /**
     *Удаление группы у контакта контакта
     */
    void removeGroupContact(String fio);

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
    void removeGroup(String name) throws ParserConfigurationException, SAXException, XPathExpressionException, TransformerException;

    /**
     *Обновление  группы
     */
    void updateGroup(List<String> attGoup) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    /**
     * Создание сущности
     */
    void addEntity (List<String> attrEntity,String command) throws IOException, TransformerException,
            ParserConfigurationException;

    /**
     * Возвращает контакт
     */
    Contact getContact(String fio);

    /**
     * Проверяет контакт на сущ.
     */
    boolean existContact(String name);

    /**
     * Проверяет группу на сущ.
     */
    boolean existGroup(String name);

}
