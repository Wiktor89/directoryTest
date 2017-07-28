package dao;

import models.Contact;
import models.Entity;
import models.Group;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 */
public interface DomSaxGroupParser {

    /**
     *Добавление группы
     */
    boolean addGroup(Entity entity) throws ParserConfigurationException, TransformerException;

    /**
     *Удаление группы
     */
    boolean removeGroup(String name);

    /**
     *Обновление  группы
     */
    boolean updateGroup(List<String> attGoup);

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroup(String name);

    /**
     *Список групп
     */
    Set<String> getGroups() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerConfigurationException;

    /**
     *Список контактов опр. группы
     */
    Set<Contact> getContactsGroup(String name);


}
