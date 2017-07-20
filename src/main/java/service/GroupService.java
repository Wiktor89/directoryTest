package service;

import models.Entity;

import java.io.IOException;

/**
 *Интерфейс для группы
 */
public interface GroupService {

    /**
     *Список контактов опр. группы
     */
    void listGroupContact();

    /**
     *Список групп
     */
    void listGroup() throws IOException;

    /**
     *Добавление группы
     */
    void addGroup(Entity entity) throws Exception;

    /**
     *Удаление группы
     */
    void remGroup() throws IOException;

    /**
     *Обновление  группы
     */
    void updGroup() throws IOException;

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroups(String nameGroup);
}
