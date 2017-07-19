package factory;

import models.Contact;
import models.Entity;

/**
 *
 */
public class ContactsFactory implements EntityFactory {

    @Override
    public Entity creatingEntity() {
        return new Contact();
    }
}
