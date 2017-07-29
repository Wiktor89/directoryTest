package controller;

import dao.DirectoryDaoImpl;
import factory.ContactFactory;
import factory.EntityFactory;
import factory.GroupFactory;
import models.Contact;
import models.Entity;
import org.xml.sax.SAXException;
import service.ContactServiceImpl;
import service.GroupServiceImpl;
import utilits.TeamList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *Реализация контроллера
 */
public class ControllerImpl implements Controller{

    /**
     * Поля инициализируются в конcтрукторе
     */
    private ContactServiceImpl serviceContact = null;
    private GroupServiceImpl serviceGroup = null;
    private DirectoryDaoImpl dao = new DirectoryDaoImpl();

    public GroupServiceImpl getServiceGroup() {
        return serviceGroup;
    }
    public void setServiceGroup(GroupServiceImpl serviceGroup) {
        this.serviceGroup = serviceGroup;
    }
    public ContactServiceImpl getServiceContact() {
        return serviceContact;
    }
    public void setServiceContact(ContactServiceImpl serviceContact) {
        this.serviceContact = serviceContact;
    }

    public ControllerImpl() {
//        this.refBook.setObservers(ViewChangModel.getViewChangModel());
        this.serviceContact = new ContactServiceImpl();
        this.serviceGroup = new GroupServiceImpl();
    }

    @Override
    public void addEntity(List<String> attrEntity,String command) throws IOException,
            TransformerException, ParserConfigurationException {
        EntityFactory entityFactory = create(command);
        Entity entity = entityFactory.creatingEntity(attrEntity);

        if (command.equalsIgnoreCase(String.valueOf(TeamList.con)))
            this.serviceContact.addContact(entity);
        if (command.equalsIgnoreCase(String.valueOf(TeamList.gro)))
            this.serviceGroup.addGroup(entity);
    }

     EntityFactory create (String command) throws IOException{
        if (command.equalsIgnoreCase(String.valueOf(TeamList.con))) return new ContactFactory();
        if (command.equalsIgnoreCase(String.valueOf(TeamList.gro))) return new GroupFactory();
        throw new IOException();
    }

    @Override
    public void updateContact(List<String> attContact) {
          this.serviceContact.updateContact(attContact);
    }

    @Override
    public void removeContact(String fio) {
        this.serviceContact.removeContact(fio);
    }

    @Override
    public void appGroupContact(List<String> attContact){
            this.serviceContact.appGroupContact(attContact);
    }

    @Override
    public void removeGroupContact(String fio) {
        this.serviceContact.removeGroupContact(fio);
    }

    @Override
    public Set<Contact> getContacts() throws ParserConfigurationException, SAXException,
            XPathExpressionException, IOException {
         return this.serviceContact.getContacts();
    }

    @Override
    public Contact getContact (String fio){
        return this.serviceContact.getContact(fio);
    }

    @Override
    public boolean existContact(String name) {
        return this.serviceContact.existContact(name);
    }

    @Override
    public boolean existGroup(String name) {
        return this.serviceGroup.existGroup(name);
    }

    @Override
    public Set<String> getContactsGroup(String name) throws ParserConfigurationException, SAXException,
            XPathExpressionException, IOException {
       return this.serviceGroup.getContactsGroup(name);
    }

    @Override
    public Set<String> getGroups() throws XPathExpressionException, ParserConfigurationException,
            SAXException, TransformerConfigurationException, IOException {
        return this.serviceGroup.getGroups();
    }

    @Override
    public void removeGroup(String name) throws ParserConfigurationException, SAXException,
            XPathExpressionException, TransformerException {
        this.serviceGroup.removeGroup(name);
    }

    @Override
    public void updateGroup(List<String> attGroup) throws ParserConfigurationException, IOException,
            SAXException, TransformerException {
            this.serviceGroup.updateGroup(attGroup);
    }

}
