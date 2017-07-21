package service;

import models.Entity;
import models.Group;

import java.io.IOException;
import java.util.Set;

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
    Set<Group> getGroups();

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
