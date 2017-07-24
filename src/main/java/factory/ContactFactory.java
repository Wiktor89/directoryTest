package factory;

import models.Contact;
import models.Entity;
import models.Group;

import java.util.List;

/**
 *
 */
public class ContactFactory implements EntityFactory {

    @Override
    public Entity creatingEntity(List<String> attrEntity) {
        String fio = attrEntity.get(0);
        Contact contact = new Contact(fio);
        String phone = attrEntity.get(1);
        String email = attrEntity.get(2);
        if (phone.trim().length() > 0 && email.trim().length() > 0){
            contact.setPhone(phone);
            contact.setEmail(email);
            contact.setGroup(new Group("нет информации"));
            return contact;
        }else if (phone.trim().length() > 0 ) {
            contact.setPhone(phone);
            contact.setEmail("нет информации");
            contact.setGroup(new Group("нет информации"));
            return contact;
        }else if (email.trim().length() > 0){
            contact.setPhone("нет информации");
            contact.setEmail(email);
            contact.setGroup(new Group("нет информации"));
            return contact;
        }else {
            contact.setGroup(new Group("нет информации"));
            return contact;
        }
    }
}
