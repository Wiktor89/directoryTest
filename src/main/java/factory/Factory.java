package factory;

import models.Entity;

import java.io.EOFException;

/**
 *Фабрика сущностей
 */
public interface Factory {

    /**
     *Создает entity по команде
     */
    Entity creatingEntity (String entity) throws EOFException;
}
