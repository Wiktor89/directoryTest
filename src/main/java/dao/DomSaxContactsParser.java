package dao;

import models.Contact;
import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
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
    boolean addContact (Entity entity);

    /**
     *Обновление контакта
     * @param attContact
     */
    boolean updateContact(List<String> attContact);

    /**
     *Удаление контакта
     */
    boolean removeContact(String fio);

    /**
     *Добавить контакту группу
     */
    boolean appGroupContact(List<String> attContact);

    /**
     *Удаление группы у контакта контакта
     */
    boolean removeGroupContact(String fio);

    /**
     *Список контактов
     */
    Set<Contact> getContacts() throws ParserConfigurationException,
            SAXException, IOException, XPathExpressionException;

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
