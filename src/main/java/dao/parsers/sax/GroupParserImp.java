package dao.parsers.sax;

import dao.DomSaxGroupParser;
import dao.parsers.handler.HandlerGroup;
import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class GroupParserImp implements DomSaxGroupParser {


    @Override
    public boolean addGroup(Entity entity) throws ParserConfigurationException, TransformerException {
        return false;
    }

    @Override
    public boolean removeGroup(String name) {
        return false;
    }

    @Override
    public void updateGroup(List<String> attGroup) {
    }

    @Override
    public boolean existGroup(String name) {
        return false;
    }

    @Override
    public Set<String> getGroups() throws ParserConfigurationException, SAXException
            , IOException, XPathExpressionException {
        SAXParserFactory factory = SAXParserFactory.newInstance();//фабрика
        HandlerGroup handlerGroup = new HandlerGroup();
        SAXParser parser = factory.newSAXParser();//получаем парсер
        parser.parse(new File("contacts.xml"), handlerGroup);//читаем из файла, слушатель
        Set<String> groups = handlerGroup.getGroups();
        return groups;
    }

    @Override
    public Set<String> getContactsGroup(String name) {
        return null;
    }
}
