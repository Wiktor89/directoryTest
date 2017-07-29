package service;

import models.Contact;
import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
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
    void addContact (Entity entity);

    /**
     *Обновление контакта
     * @param attContact
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
    Set<Contact> getContacts() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException;

    /**
     *Проверка контакта на существование
     */
    boolean existContact(String name);

    /**
     *Возвращает контакт
     */
    Contact getContact(String fio);

    /**
     *Поиск контакта по имени
     */
    Contact searchName (String fio);
}
