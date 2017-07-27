package dao.parsers.handler;

import models.Group;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sorted.GroupNameComparator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class HandlerGroup extends DefaultHandler {

    private int id;
    private String data;
    private Set<Group> groups = new TreeSet<>(new GroupNameComparator());

    @Override
    public void startElement(String nameSpace, String localName, String qName, Attributes attributes) throws SAXException{
        data = qName;
        if (data.equalsIgnoreCase("group")){
            id = Integer.parseInt(attributes.getValue("id"));
        }
    }

    @Override
    public void endElement(String nameSpace, String localName, String qName) throws SAXException{
        data = "";
        id = -1;

    }

    @Override
    public void characters(char [] chars, int start, int end){
        if (data.equalsIgnoreCase("title")){
            String s = new String(chars,start,end);
            Group group = new Group(s);
            group.setId(id);
            groups.add(group);
        }
    }

    public Set<Group> getGroups(){
        return groups;
    }
}
