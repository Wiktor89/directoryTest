package dao;

import javafx.beans.Observable;
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
 *
 */
public interface DomSaxGroupParser  {

    /**
     *Добавление группы
     */
    void addGroup(Entity entity) throws ParserConfigurationException,
            TransformerException, IOException, SAXException;

    /**
     *Удаление группы
     */
    boolean removeGroup(String name) throws ParserConfigurationException, SAXException
            , XPathExpressionException, TransformerException, IOException;

    /**
     *Обновление  группы
     * @param attGroup
     */
    void updateGroup(List<String> attGroup) throws ParserConfigurationException
            , SAXException, TransformerException, IOException;

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroup(String name) throws ParserConfigurationException, SAXException, IOException;

    /**
     *Список групп
     */
    Set<String> getGroups() throws ParserConfigurationException, SAXException,
            XPathExpressionException, TransformerConfigurationException, IOException;

    /**
     *Список контактов опр. группы
     */
    Set<String> getContactsGroup(String name) throws ParserConfigurationException, SAXException,
            XPathExpressionException, IOException;


}
