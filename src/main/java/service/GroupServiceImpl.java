package service;

import dao.DomSaxGroupParser;
import dao.parsers.dom.DomGroupParserImp;
import models.Entity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.*;

/**
 *Сервис для контакта
 */
public class GroupServiceImpl implements GroupService {

    /**
     *consol
     * refBook
     * contacts
     * groups
     * dao
     */
    private DomSaxGroupParser dao = new DomGroupParserImp();

    public GroupServiceImpl() {
    }


    @Override
    public Set<String> getContactsGroup(String name) throws ParserConfigurationException,
            SAXException, XPathExpressionException, IOException {
        return this.dao.getContactsGroup(name);
    }

    @Override
    public Set<String> getGroups() throws TransformerConfigurationException, ParserConfigurationException,
            SAXException, XPathExpressionException, IOException {
        return this.dao.getGroups();
    }

    @Override
    public void addGroup(Entity entity) throws TransformerException,
            ParserConfigurationException {
        this.dao.addGroup(entity);
    }

    @Override
    public void removeGroup(String name) throws ParserConfigurationException,
            TransformerException, SAXException, XPathExpressionException {
        this.dao.removeGroup(name);
    }

    @Override
    public void updateGroup(List<String> attGroup) throws ParserConfigurationException, TransformerException
            , SAXException, IOException {
        this.dao.updateGroup(attGroup);
    }

    @Override
    public boolean existGroup(String name){
        return this.dao.existGroup(name);
    }
}
