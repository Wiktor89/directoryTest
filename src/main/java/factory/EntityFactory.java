package factory;

import models.Contact;
import models.Entity;
import models.Group;
import utilits.TeamList;
import java.io.EOFException;
import java.util.List;

/**
 * Фабрика для создания контактов и групп
 */
public class EntityFactory implements Factory {

    @Override
    public Entity creatingEntity(List<String> attrEntity) throws EOFException {
        Entity entity = new Contact();
        return entity;
    }

//    static Entity creatingEntityq(List<String> attrEntity) throws EOFException{
//        if (attrEntity.size() >= 1 ){
//            String name = attrEntity.get(0);
//            String phone = attrEntity.get(1);
//            String email = attrEntity.get(2);
//            if (name.trim().length() > 0 && phone.trim().length() == 0 && email.trim().length() == 0){
//                return new Contact();
//            }
//
//        }else if (attrEntity.size() > )
//        if (name.trim().length() > 0) return new Group();
//        throw new EOFException();
//    }
//      В фабрике должно быть два метода createEntity с разными наборами параметров.
//      Один из них должен создавать контакт, другой должен создавать группу.
//      Оба метода должны возвращать Entity
}
