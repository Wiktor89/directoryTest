package parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import dao.DomSaxGroups;
import models.Entity;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;
import views.ViewChangGroup;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 *Jac парсер для группы
 */
public class GroupsJackson extends Observable implements DomSaxGroups {

    private ViewChangGroup model = null;

    public GroupsJackson() {
        model = ViewChangGroup.getViewChangGroup();
    }

    @Override
    public void addGroup(Entity entity) throws ParserConfigurationException,
            TransformerException, IOException, SAXException {
        models.Group group = (models.Group) entity;
        Group group1 = new Group();
        group1.setId(String.valueOf(group.getId()));
        group1.setTitle(group.getName());
        ObjectMapper objectMapper = new XmlMapper();
        Groups groupsList = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("groups.xml")), StandardCharsets.UTF_8),
                Groups.class);
        List<Group> groups = groupsList.getGroup();
        groups.add(group1);
        groupsList.setGroupJacs(groups);
        XmlMapper mapper = new XmlMapper();
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
        mapper.writeValue(new File("groups.xml"),groupsList);
        Set<String> groupss = new TreeSet<>();
        for (Group group2 : groups){
            groupss.add(group2.getTitle());
        }
        model.update(this,groupss);

    }

    @Override
    public boolean removeGroup(String name) throws ParserConfigurationException,
            SAXException, XPathExpressionException, TransformerException, IOException {
        boolean result = false;
        ObjectMapper objectMapper = new XmlMapper();
        Groups groupsList = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("groups.xml")), StandardCharsets.UTF_8),
                Groups.class);
        List<Group> groups = groupsList.getGroup();
        for (Group group : groups){
            if (group.getTitle().equalsIgnoreCase(name)){
                groups.remove(group);
                result = true;
                break;
            }
        }
        XmlMapper mapper = new XmlMapper();
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
        mapper.writeValue(new File("groups.xml"),groupsList);
        Set<String> groupss = new TreeSet<>();
        for (Group group2 : groups){
            groupss.add(group2.getTitle());
        }
        model.update(this,groupss);
        return result;
    }

    @Override
    public void updateGroup(List<String> attGroup) throws ParserConfigurationException,
            SAXException, TransformerException, IOException {
        String oldName = attGroup.get(0);
        String newName = attGroup.get(1);
        ObjectMapper objectMapper = new XmlMapper();
        Groups employees = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("groups.xml")), StandardCharsets.UTF_8),
                Groups.class);
        List<Group> groups = employees.getGroup();
        for (Group group : groups){
            if (group.getTitle().equalsIgnoreCase(oldName)){
                group.setTitle(newName);
                XmlMapper mapper = new XmlMapper();
                mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
                mapper.writeValue(new File("groups.xml"),employees);
                Set<String> groupss = null;
                try {
                    groupss = getGroups();
                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }
                model.update(this,groupss);
            }
        }

    }

    @Override
    public boolean existGroup(String name) throws ParserConfigurationException,
            SAXException, IOException {
        boolean result = false;
        ObjectMapper objectMapper = new XmlMapper();
        Groups employees = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("groups.xml")), StandardCharsets.UTF_8),
                Groups.class);
        List<Group> contact = employees.getGroup();
        for (Group group : contact){
            if (group.getTitle().equalsIgnoreCase(name)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public Set<String> getGroups() throws ParserConfigurationException, SAXException,
            XPathExpressionException, TransformerConfigurationException, IOException {
        Set<String> groups = new TreeSet<>();
        ObjectMapper objectMapper = new XmlMapper();
        Groups employees = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("groups.xml")), StandardCharsets.UTF_8),
                Groups.class);
        List<Group> groupss = employees.getGroup();
        for (Group group : groupss){
            groups.add(group.getTitle());
        }
        return groups;
    }

    @Override
    public Set<String> getContactsGroup(String name) throws ParserConfigurationException,
            SAXException, XPathExpressionException, IOException {
        Set<String> contacts = new TreeSet<>();
        ObjectMapper objectMapper = new XmlMapper();
        Contacts employees = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("contacts.xml")), StandardCharsets.UTF_8),
                Contacts.class);
        List<Contact> contact = employees.getContact();
        for (Contact contactJac : contact){
            if (contactJac.getGroup().equalsIgnoreCase(name)){
                contacts.add(contactJac.getName());
            }
        }
        return contacts;
    }

}
