package service;

import models.Contact;
import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Интерфейс для контактов
 */
public interface ContactService {

    /**
     *Добавление контакта
     */
    void addContact (Entity entity) throws IOException, ParserConfigurationException, SAXException, TransformerException;

    /**
     *Обновление контакта
     * @param attContact
     */
    void updateContact(List<String> attContact) throws IOException, SAXException, ParserConfigurationException;

    /**
     *Удаление контакта
     */
    void removeContact(String fio) throws SAXException, TransformerException, ParserConfigurationException, IOException;

    /**
     *Добавить контакту группу
     */
    void appGroupContact(List<String> attContact) throws SAXException, TransformerException, ParserConfigurationException, IOException;

    /**
     *Удаление группы у контакта контакта
     */
    void removeGroupContact(String fio) throws ParserConfigurationException, SAXException, IOException, TransformerException;

    /**
     *Список контактов
     */
    Set<Contact> getContacts() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException;

    /**
     *Проверка контакта на существование
     */
    boolean existContact(String name) throws ParserConfigurationException, TransformerException, SAXException, IOException;

    /**
     *Возвращает контакт
     */
    Contact getContact(String fio) throws IOException, SAXException, ParserConfigurationException;

    /**
     *Поиск контакта по имени
     */
    Contact searchName (String fio);
}
