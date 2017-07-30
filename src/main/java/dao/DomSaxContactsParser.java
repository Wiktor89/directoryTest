package dao;

import models.Contact;
import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 */
public interface DomSaxContactsParser {

    /**
     *Добавление контакта
     */
    boolean addContact (Entity entity) throws TransformerException, IOException, SAXException, ParserConfigurationException;

    /**
     *Обновление контакта
     * @param attContact
     */
    boolean updateContact(List<String> attContact) throws ParserConfigurationException, IOException, SAXException;

    /**
     *Удаление контакта
     */
    boolean removeContact(String fio) throws IOException, SAXException, ParserConfigurationException, TransformerException;

    /**
     *Добавить контакту группу
     */
    boolean appGroupContact(List<String> attContact) throws IOException, SAXException, ParserConfigurationException, TransformerException;

    /**
     *Удаление группы у контакта контакта
     */
    boolean removeGroupContact(String fio) throws IOException, SAXException, ParserConfigurationException, TransformerException;

    /**
     *Список контактов
     */
    Set<Contact> getContacts() throws ParserConfigurationException,
            SAXException, IOException, XPathExpressionException;

    /**
     *Проверка контакта на существование
     */
    boolean existContact(String name) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    /**
     *Возвращает контакт
     */
    Contact getContact(String fio) throws ParserConfigurationException, IOException, SAXException;

    /**
     *Поиск контакта по имени
     */
    Contact searchName (String fio);


}
