package dao;

import models.Contact;
import models.Entity;
import models.Group;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 */
public interface ParserGroupDomSax {

    /**
     *Добавление группы
     */
    boolean addGroup(Entity entity) throws ParserConfigurationException, TransformerException;

    /**
     *Удаление группы
     */
    boolean remGroup(String name);

    /**
     *Обновление  группы
     */
    boolean updGroup(List<String> attGoup);

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroup(String name);

    /**
     *Список групп
     */
    Set<Group> getGroups() throws ParserConfigurationException, SAXException, IOException;

    /**
     *Список контактов опр. группы
     */
    Set<Contact> getContactsGroup(String name);


}
