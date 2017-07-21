package service;

import models.Contact;
import models.Entity;
import models.Group;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *Интерфейс для группы
 */
public interface GroupService {

    /**
     *Список контактов опр. группы
     */
    List<Contact> getContactsGroup(String name);

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
    void remGroup(String name) throws IOException;

    /**
     *Обновление  группы
     */
    void updGroup(Group group,String oldName);

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroups(String nameGroup);
}
