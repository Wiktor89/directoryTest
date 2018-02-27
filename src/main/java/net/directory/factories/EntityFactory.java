package net.directory.factories;

import net.directory.models.Contact;
import net.directory.models.Entity;
import net.directory.models.Group;
import net.directory.models.User;
import net.directory.utilits.TeamList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 *Фабрика для создания контактов и групп
 */
public class EntityFactory {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityFactory.class);
    public EntityFactory() {
    }

    private static Entity getContact(List<String> attrEntity){
        if (attrEntity.size() == 1){
            return new Contact(attrEntity.get(0));
        }
       Contact contact = new Contact(attrEntity.get(0));
       String phone = attrEntity.get(1);
       String email = attrEntity.get(2);
       if (phone.trim().length() > 0 && email.trim().length() > 0){
           contact.setPhone(phone);
           contact.setEmail(email);
       }else if (phone.trim().length() > 0 ) {
           contact.setPhone(phone);
       }else if (email.trim().length() > 0){
           contact.setEmail(email);
       }
        contact.setUser(new User());
        LOGGER.info("add entity "+contact);
        return contact;
    }

     private static Entity getGroup(List<String> attrEntity){
        return new Group(attrEntity.get(0));
    }

    public static Entity create (String command,List<String> attrEntity) throws IOException{
        if (command.equalsIgnoreCase(String.valueOf(TeamList.con))) return getContact(attrEntity);
        if (command.equalsIgnoreCase(String.valueOf(TeamList.gro))) return getGroup(attrEntity);
        throw new IOException();
    }
}
