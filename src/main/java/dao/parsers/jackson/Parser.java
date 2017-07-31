package dao.parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 */
public class Parser {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new XmlMapper();
        Groups employees = objectMapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(
                Paths.get("groups.xml")), StandardCharsets.UTF_8),
                Groups.class);
        List<GroupJac> contact = employees.getGroup();
        for (GroupJac contactJac : contact){
            System.out.println(contactJac);
        }

    }
}
