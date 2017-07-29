package models;
import dao.DomSaxGroupParser;
import dao.parsers.dom.DomContactParserImp;
import dao.parsers.dom.DomGroupParserImp;
import dao.parsers.sax.GroupParserImp;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 *
 */
public class Main {
    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        new Main().goo();
    }

    public void goo() throws TransformerException, ParserConfigurationException, IOException, SAXException, XPathExpressionException {
//        ViewInf viewInf  = new ViewInf();
//        viewInf.startPage();
//        Entity entity = new Group();
//        DomGroupParserImp parserGroupImpDom = new DomGroupParserImp();
//        parserGroupImpDom.addGroup(entity);

//        DomSaxGroupParser sax = new DomGroupParserImp();
//        sax.getGroups();
//        DomSaxContactsParser domSax = new ContactParserImpl();
//        domSax.getContacts();

        DomGroupParserImp domContactParserImp = new DomGroupParserImp();
        List<String> strings = new ArrayList<>();
        strings.add()
        boolean test = domContactParserImp.updateGroup("test");
        System.out.println(test);
//        for (String s : contacts){
//            System.out.println(s);
//        }
    }
}
