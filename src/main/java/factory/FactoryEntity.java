package factory;

import models.Contact;
import models.Entity;
import models.Group;
import utilits.TeamList;
import java.io.EOFException;

/**
 * Фабрика entity
 */
public class FactoryEntity implements Factory {

    @Override
    public Entity creatingEntity(String command) throws EOFException {
        Entity entity = creatingEntityq(command);
        return entity;
    }

    static Entity creatingEntityq(String entity) throws EOFException{
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.con))) return new Contact();
        if (entity.equalsIgnoreCase(String.valueOf(TeamList.gro))) return new Group();
        throw new EOFException();
    }
}
