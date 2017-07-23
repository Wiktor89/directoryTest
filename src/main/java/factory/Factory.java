package factory;

import models.Entity;

import java.io.EOFException;
import java.util.List;

/**
 *Фабрика сущностей
 */
public interface Factory {

    /**
     *Создает entity по команде
     */
    Entity creatingEntity (List<String> entity) throws EOFException;

}
