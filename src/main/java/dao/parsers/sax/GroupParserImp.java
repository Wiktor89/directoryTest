package dao.parsers.sax;

import dao.DomSaxGroupParser;
import dao.parsers.handler.HandlerGroup;
import models.Contact;
import models.Entity;
import models.Group;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
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
    public boolean remGroup(String name) {
        return false;
    }

    @Override
    public boolean updGroup(List<String> attGoup) {
        return false;
    }

    @Override
    public boolean existGroup(String name) {
        return false;
    }

    @Override
    public Set<Group> getGroups() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();//фабрика
        HandlerGroup handlerGroup = new HandlerGroup();
        SAXParser parser = factory.newSAXParser();//получаем парсер
        parser.parse(new File("refbook.xml"), handlerGroup);//читаем из файла, слушатель
        Set<Group> groups = handlerGroup.getGroups();
        for (Group group : groups){
            System.out.println(group);
        }
        return groups;
    }

    @Override
    public Set<Contact> getContactsGroup(String name) {
        return null;
    }
}