package factory;

import models.Entity;
import models.Group;
import java.util.List;

/**
 *
 */
public class GroupFactory implements EntityFactory {

    @Override
    public Entity creatingEntity(List<String> attrEntity) {
        return new Group(attrEntity.get(0));
    }
}
