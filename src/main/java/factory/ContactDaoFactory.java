package factory;

import dao.DomSaxContactsParser;
import dao.parsers.dom.DomContactParserImp;
import dao.parsers.jackson.ContactsJacksonParser;
import dao.parsers.sax.ContactParserImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class ContactDaoFactory {

   public DomSaxContactsParser getContactParser (){
        DomSaxContactsParser parser = createGroupParser(command());
        return parser;

    }

    static DomSaxContactsParser createGroupParser(String command){
        if (command.equalsIgnoreCase("dom")){
            return new DomContactParserImp();
        }if(command.equalsIgnoreCase("sax")){
            return new ContactParserImpl();
        }if (command.equalsIgnoreCase("jac")){
            return new ContactsJacksonParser();
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
