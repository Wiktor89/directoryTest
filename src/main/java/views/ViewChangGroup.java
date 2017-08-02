package views;

import dao.GroupDao;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 *Доп. вью для демонстрации Observer
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
        if (o instanceof GroupDao){
            Set<String> groups  = null;
            try {
                groups = ((GroupDao) o).getGroups();
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
