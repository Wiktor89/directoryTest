package factory;

import models.Entity;

import java.util.List;

/**
 * Фабрика для создания контактов и групп
 */
public interface EntityFactory {

     Entity creatingEntity(List<String> attrEntity);

}
