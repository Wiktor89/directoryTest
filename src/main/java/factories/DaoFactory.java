package factories;

import dao.ContactDao;
import dao.GroupDao;

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
        return new dao.database.ContactsDaoImpl();
    }
    public static GroupDao getGroupDao(){
        return new dao.database.GroupsDaoImpl();
    }

    public static String [] command (){
        String [] parser = new String[2];
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("configDb.properties");
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
