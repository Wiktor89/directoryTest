package factories;

import dao.DomSaxGroups;
import parsers.dom.DomGroupsImp;
import parsers.jackson.GroupsJackson;
import parsers.sax.SaxGroupsImp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class AbstractFactoryDaoGroups {

    public DomSaxGroups getGroupParser (){
        DomSaxGroups parser = createGroupParser(command());
        return parser;
    }

    static DomSaxGroups createGroupParser (String command){

        if (command.equalsIgnoreCase("dom")){
            return new DomGroupsImp();
        }if(command.equalsIgnoreCase("sax")){
            return new SaxGroupsImp();
        }if (command.equalsIgnoreCase("jac")){
            return new GroupsJackson();
        }
        return null;
    }

    private String command (){

        InputStream input = null;
        Properties prop = new Properties();
        String parser = null;

        try {

            input = new FileInputStream("config.properties");

            prop.load(input);
            parser = prop.getProperty("contactParser");

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
