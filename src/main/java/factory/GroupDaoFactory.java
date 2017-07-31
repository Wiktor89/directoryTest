package factory;

import dao.DomSaxGroupParser;
import dao.parsers.dom.DomGroupParserImp;
import dao.parsers.sax.GroupParserImp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class GroupDaoFactory {

    public DomSaxGroupParser getGroupParser (){
        DomSaxGroupParser parser = createGroupParser(command());
        return parser;
    }

    static DomSaxGroupParser createGroupParser (String command){

        if (command.equalsIgnoreCase("dom")){
            return new DomGroupParserImp();
        }if(command.equalsIgnoreCase("sax")){
            return new GroupParserImp();
        }if (command.equalsIgnoreCase("jackson")){
            return null;
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
