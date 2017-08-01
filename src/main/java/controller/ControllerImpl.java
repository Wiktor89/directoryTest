package controller;

import factory.*;
import models.Contact;
import models.Entity;
import org.xml.sax.SAXException;
import service.ContactServiceImpl;
import service.GroupServiceImpl;
import utilits.TeamList;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *Реализация контроллера
 */
public class ControllerImpl implements Controller{

    /**
     * Поля инициализируются в конcтрукторе
     */
    private ContactServiceImpl serviceContact = null;
    private GroupServiceImpl serviceGroup = null;

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
        validationStorage();
        GroupDaoFactory groupDaoFactory = new GroupDaoFactory();
        ContactDaoFactory contactDaoFactory = new ContactDaoFactory();
        this.serviceContact = new ContactServiceImpl(contactDaoFactory.getContactParser());
        this.serviceGroup = new GroupServiceImpl(groupDaoFactory.getGroupParser());
    }

    @Override
    public void addEntity(List<String> attrEntity,String command) throws IOException,
            TransformerException, ParserConfigurationException, SAXException {
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
    public void updateContact(List<String> attContact) throws ParserConfigurationException,
            SAXException, IOException, TransformerException {
          this.serviceContact.updateContact(attContact);
    }

    @Override
    public void removeContact(String fio) throws SAXException, ParserConfigurationException,
            IOException, TransformerException {
        this.serviceContact.removeContact(fio);
    }

    @Override
    public void appGroupContact(List<String> attContact) throws SAXException, ParserConfigurationException,
            IOException, TransformerException {
            this.serviceContact.appGroupContact(attContact);
    }

    @Override
    public void removeGroupContact(String fio) throws IOException, SAXException,
            ParserConfigurationException, TransformerException {
        this.serviceContact.removeGroupContact(fio);
    }

    @Override
    public Set<Contact> getContacts() throws ParserConfigurationException, SAXException,
            XPathExpressionException, IOException {
         return this.serviceContact.getContacts();
    }

    @Override
    public Contact getContact (String fio) throws ParserConfigurationException, SAXException, IOException {
        return this.serviceContact.getContact(fio);
    }

    @Override
    public boolean existContact(String name) throws ParserConfigurationException,
            IOException, SAXException, TransformerException {
        return this.serviceContact.existContact(name);
    }

    @Override
    public boolean existGroup(String name) throws ParserConfigurationException, SAXException, IOException {
        return this.serviceGroup.existGroup(name);
    }

    @Override
    public String searchName(String fio) throws ParserConfigurationException,
            SAXException, IOException {
        return this.serviceContact.searchName(fio);
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
            XPathExpressionException, TransformerException, IOException {
        this.serviceGroup.removeGroup(name);
    }

    @Override
    public void updateGroup(List<String> attGroup) throws ParserConfigurationException,
            IOException,
            SAXException, TransformerException {
            this.serviceGroup.updateGroup(attGroup);
    }

    private void validationStorage() {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("contact.xsd","contacts.xml");
        stringMap.put("group.xsd","groups.xml");

        for (Map.Entry<String,String> entry : stringMap.entrySet()){
            String schemaXsd = entry.getKey();
            String validatorXml = entry.getValue();
            try {
                SchemaFactory factory =
                        SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = factory.newSchema(new File(schemaXsd));
                Validator validator = schema.newValidator();
                validator.validate(new StreamSource(new File(validatorXml)));
            } catch (IOException e){
                System.out.println("Exception: "+e.getMessage());
            }catch(SAXException e1){
                System.out.println("SAX Exception: "+e1.getMessage());
            }
        }
    }


}
