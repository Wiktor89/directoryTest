package models;
import dao.ParserContactsDomSax;
import dao.ParserGroupDomSax;
import dao.parsers.dom.ParserGroupImpDom;
import dao.parsers.sax.ParserContactImpSax;
import dao.parsers.sax.ParserGroupImpSax;
import org.xml.sax.SAXException;
import views.ViewInf;

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
//        ParserGroupImpDom parserGroupImpDom = new ParserGroupImpDom();
//        parserGroupImpDom.addGroup(entity);

        ParserGroupDomSax sax = new ParserGroupImpSax();
        sax.getGroups();
//        ParserContactsDomSax domSax = new ParserContactImpSax();
//        domSax.getContacts();
    }
}
