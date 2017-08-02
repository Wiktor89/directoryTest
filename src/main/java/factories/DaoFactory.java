package factories;

import dao.ContactDao;
import dao.GroupDao;
import dao.dom.ContactsImp;
import dao.jackson.ContactsJackson;
import dao.jackson.GroupsJackson;
import dao.sax.ContactsImpl;
import dao.sax.GroupsImp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *Фабрика Dao
 */
public class DaoFactory {

    public DaoFactory() {

    }

    public static ContactDao getContactDao(){
        String[] command1 = command();
        if (command1[1].equalsIgnoreCase("dom")){
            return new ContactsImp();
        }if(command1[1].equalsIgnoreCase("sax")){
            return new ContactsImpl();
        }if (command1[1].equalsIgnoreCase("jac")){
            return new ContactsJackson();
        }
        return null;
    }

    public static GroupDao getGroupDao(){
        String[] command1 = command();
        if (command1[0].equalsIgnoreCase("dom")){
            return new dao.dom.GroupsImp();
        }if(command1[0].equalsIgnoreCase("sax")){
            return new GroupsImp();
        }if (command1[0].equalsIgnoreCase("jac")){
            return new GroupsJackson();
        }
        return null;
    }

    public static String [] command (){
        String [] parser = new String[2];
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            prop.load(input);
            parser[0] = prop.getProperty("groupParser");
            parser[1] = prop.getProperty("contactParser");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return parser;
    }
}
