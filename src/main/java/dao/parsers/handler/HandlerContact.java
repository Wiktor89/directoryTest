package dao.parsers.handler;

import models.Contact;
import models.Group;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sorted.ContactFioComparator;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class HandlerContact extends DefaultHandler {


    private int id;
    private String data;
    private Contact contact;
    private Set<Contact> contacts = new TreeSet<>(new ContactFioComparator());

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException{
    }

    @Override
    public void startElement(String nameSpace, String localName, String qName, Attributes attributes) throws SAXException {
        data = qName;
        if (data.equalsIgnoreCase("contact")) {
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
        if (this.data.equalsIgnoreCase("data")){
            String s = new String(chars,start,end);
            this.contact = new Contact(s);
        }if (this.data.equalsIgnoreCase("phone")){
            String s = new String(chars,start,end);
            this.contact.setPhone(s);
        }if (this.data.equalsIgnoreCase("email")){
            String s = new String(chars,start,end);
            this.contact.setEmail(s);
            contact.setGroup(new Group("нет группы"));
            contacts.add(contact);
        }
    }

    public Set<Contact> getContacts(){
        return contacts;
    }
}
