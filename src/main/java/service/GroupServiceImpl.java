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
    private DomSaxGroupParser dao = null;

    public GroupServiceImpl(DomSaxGroupParser dao) {
        this.dao = dao;
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
            ParserConfigurationException, IOException, SAXException {
        this.dao.addGroup(entity);
    }

    @Override
    public void removeGroup(String name) throws ParserConfigurationException,
            TransformerException, SAXException, XPathExpressionException, IOException {
        this.dao.removeGroup(name);
    }

    @Override
    public void updateGroup(List<String> attGroup) throws ParserConfigurationException, TransformerException
            , SAXException, IOException {
        this.dao.updateGroup(attGroup);
    }

    @Override
    public boolean existGroup(String name) throws IOException, SAXException, ParserConfigurationException {
        return this.dao.existGroup(name);
    }
}
