package dao.parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dao.DomSaxGroupParser;
import models.Entity;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class GroupJacksonParser implements DomSaxGroupParser {

    @Override
    public void addGroup(Entity entity) throws ParserConfigurationException,
            TransformerException, IOException, SAXException {




    }

    @Override
    public boolean removeGroup(String name) throws ParserConfigurationException,
            SAXException, XPathExpressionException, TransformerException, IOException {
        boolean result = false;
        ObjectMapper objectMapper = new XmlMapper();
        Groups employees = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("groups.xml")), StandardCharsets.UTF_8),
                Groups.class);
        List<GroupJac> groups = employees.getGroup();
        for (GroupJac group : groups){
            if (group.getTitle().equalsIgnoreCase(name)){
                groups.remove(group);
                result = true;
            }
        }
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
        List<GroupJac> groups = employees.getGroup();
        for (GroupJac group : groups){
            if (group.getTitle().equalsIgnoreCase(oldName)){
                group.setTitle(newName);
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
        List<GroupJac> contact = employees.getGroup();
        for (GroupJac group : contact){
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
        List<GroupJac> contact = employees.getGroup();
        for (GroupJac contactJac : contact){
            groups.add(contactJac.getTitle());
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
        List<ContactJac> contact = employees.getContact();
        for (ContactJac contactJac : contact){
            if (contactJac.getGroup().equalsIgnoreCase(name)){
                contacts.add(contactJac.getTitle());
            }
        }
        return contacts;
    }
}
