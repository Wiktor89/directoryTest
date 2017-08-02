package factories;

import models.Entity;

import java.util.List;

/**
 * Фабрика для создания контактов и групп
 */
public interface FactoryEntity {

     /**
      * Создание сущности
      */
     Entity creatingEntity(List<String> attrEntity);

}
