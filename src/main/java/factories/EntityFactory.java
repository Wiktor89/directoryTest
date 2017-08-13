package factories;

import models.Contact;
import models.Entity;
import models.Group;
import models.User;
import utilits.TeamList;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *Фабрика для создания контактов и групп
 */
public class EntityFactory {

    public EntityFactory() {
    }

    private static Entity getContact(List<String> attrEntity){
        Set<Group> groups = new TreeSet<>();
       Contact contact = new Contact(attrEntity.get(0));
       String phone = attrEntity.get(1);
       String email = attrEntity.get(2);
       if (phone.trim().length() > 0 && email.trim().length() > 0){
           contact.setPhone(phone);
           contact.setEmail(email);
           contact.setGroup(groups);
       }else if (phone.trim().length() > 0 ) {
           contact.setPhone(phone);
           contact.setEmail("нет информации");
       }else if (email.trim().length() > 0){
           contact.setPhone("нет информации");
           contact.setEmail(email);
       }
        groups.add(new Group("нет группы"));
        contact.setGroup(groups);
        contact.setUser(new User());
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
