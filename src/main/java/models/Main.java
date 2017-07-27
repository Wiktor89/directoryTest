package models;
import dao.DomSaxGroupParser;
import dao.parsers.sax.GroupParserImp;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;


/**
 *
 */
public class Main {
    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        new Main().goo();
    }

    public void goo() throws TransformerException, ParserConfigurationException, IOException, SAXException {
//        ViewInf viewInf  = new ViewInf();
//        viewInf.startPage();
//        Entity entity = new Group();
//        GroupParserImp parserGroupImpDom = new GroupParserImp();
//        parserGroupImpDom.addGroup(entity);

        DomSaxGroupParser sax = new GroupParserImp();
        sax.getGroups();
//        DomSaxContactsParser domSax = new ContactParserImpl();
//        domSax.getContacts();
    }
}
