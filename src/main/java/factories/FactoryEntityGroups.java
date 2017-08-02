package factories;

import models.Entity;
import models.Group;
import java.util.List;

/**
 *
 */
public class FactoryEntityGroups implements FactoryEntity {

    @Override
    public Entity creatingEntity(List<String> attrEntity) {
        return new Group(attrEntity.get(0));
    }
}
