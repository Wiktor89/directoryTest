package factory;

import models.Entity;
import models.Group;

/**
 *
 */
public class GroupFactory implements EntityFactory {

    @Override
    public Entity creatingEntity() {
        return new Group();
    }
}
