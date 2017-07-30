package models;

import org.xml.sax.SAXException;
import views.ViewImpl;
import views.ViewInf;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;


/**
 *
 */
public class Main {
    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        new Main().goo();
    }

    public void goo() throws TransformerException, ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        ViewInf viewInf = new ViewInf();
        viewInf.startPage();
    }
}
