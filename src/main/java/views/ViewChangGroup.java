package views;

import dao.DomSaxContactsParser;
import dao.DomSaxGroupParser;
import models.Contact;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 *
 */
public class ViewChangGroup implements Observer {


    private static ViewChangGroup viewChangGroup;

    public static ViewChangGroup getViewChangGroup(){
        if (viewChangGroup == null){
            viewChangGroup = new ViewChangGroup();
        }
        return viewChangGroup;
    }

    private ViewChangGroup() {
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof DomSaxGroupParser){
            Set<String> groups  = null;
            try {
                groups = ((DomSaxGroupParser) o).getGroups();
            } catch (ParserConfigurationException | IOException | TransformerConfigurationException | XPathExpressionException | SAXException e) {
                e.printStackTrace();
            }
            if (!groups.isEmpty()){
                System.out.println("******************************");
                for (String group : groups){
                    System.out.println(group);
                }
                System.out.println("******************************");
            }else {
                System.out.println("список групп пуст");
            }
        }
    }

}
