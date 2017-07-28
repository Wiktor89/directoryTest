package dao.parsers.sax;

import dao.DomSaxGroupParser;
import dao.parsers.Parser;
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
 *
 */
public class GroupParserImp extends Parser implements DomSaxGroupParser {


    @Override
    public boolean addGroup(Entity entity) throws ParserConfigurationException, TransformerException {
        return false;
    }

    @Override
    public boolean removeGroup(String name) {
        return false;
    }

    @Override
    public boolean updateGroup(List<String> attGoup) {
        return false;
    }

    @Override
    public boolean existGroup(String name) {
        return false;
    }

    @Override
    public Set<String> getGroups() throws ParserConfigurationException, SAXException
            , IOException, XPathExpressionException {
        return super.getGroups();
//        SAXParserFactory factory = SAXParserFactory.newInstance();//фабрика
//        HandlerGroup handlerGroup = new HandlerGroup();
//        SAXParser parser = factory.newSAXParser();//получаем парсер
//        parser.parse(new File("refbook.xml"), handlerGroup);//читаем из файла, слушатель
//        Set<String> groups = handlerGroup.getGroups();
//        for (String group : groups){
//            System.out.println(group);
//        }

    }

    @Override
    public Set<Contact> getContactsGroup(String name) {
        return null;
    }
}
