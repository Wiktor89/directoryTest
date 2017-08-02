package factories;

import dao.DomSaxContacts;
import parsers.dom.DomContactImp;
import parsers.jackson.ContactsJackson;
import parsers.sax.SaxContactImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class AbstractFactoryDaoContacts {

   public DomSaxContacts getContactParser (){
        DomSaxContacts parser = createGroupParser(command());
        return parser;

    }

    static DomSaxContacts createGroupParser(String command){
        if (command.equalsIgnoreCase("dom")){
            return new DomContactImp();
        }if(command.equalsIgnoreCase("sax")){
            return new SaxContactImpl();
        }if (command.equalsIgnoreCase("jac")){
            return new ContactsJackson();
        }
        return null;
    }

    private String command (){
        String parser = null;
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            prop.load(input);
            parser = prop.getProperty("groupParser");

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
