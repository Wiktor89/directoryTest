package dao.sax.handler;

import models.Contact;
import models.Group;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class ContactHandler extends DefaultHandler {


    private String data;
    private Contact contact;
    private Set<Contact> contacts = new TreeSet<>();

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException{
    }

    @Override
    public void startElement(String nameSpace, String localName, String qName, Attributes attributes) throws SAXException {
        data = qName;
        if (data.equalsIgnoreCase("name")){
            contact = new Contact();
        }
    }

    @Override
    public void endElement(String nameSpace, String localName, String qName) throws SAXException{
        data = "";

    }

    @Override
    public void characters(char [] chars, int start, int end){
        if (this.data.equalsIgnoreCase("name")){
            String s = new String(chars,start,end);
            this.contact.setFio(s);
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
