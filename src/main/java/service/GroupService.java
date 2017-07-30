package service;

import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *Интерфейс для группы
 */
public interface GroupService {

    /**
     *Список контактов опр. группы
     */
    Set<String> getContactsGroup(String name) throws ParserConfigurationException,
            SAXException, XPathExpressionException, IOException;

    /**
     *Список групп
     */
    Set<String> getGroups() throws TransformerConfigurationException, ParserConfigurationException,
            SAXException, XPathExpressionException, IOException;

    /**
     *Добавление группы
     */
    void addGroup(Entity entity) throws TransformerException,
            ParserConfigurationException, IOException, SAXException;

    /**
     *Удаление группы
     */
    void removeGroup(String name) throws ParserConfigurationException,
            TransformerException, SAXException, XPathExpressionException, IOException;

    /**
     *Обновление  группы
     */
    void updateGroup(List<String> attGoup) throws ParserConfigurationException, TransformerException
            , SAXException, IOException;

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroup(String name);
}
