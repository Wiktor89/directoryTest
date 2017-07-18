package service;

import java.io.IOException;

/**
 *Интерфейс для группы
 */
public interface GroupService {

    /**
     *Список контактов опр. группы
     */
    void listGroupContact(String nameGroup);

    /**
     *Список групп
     */
    void listGroup() throws Exception;

    /**
     *Добавление группы
     */
    void addGroup(String nameGroup) throws IOException;

    /**
     *Удаление группы
     */
    void removeGroup(String nameGroup);

    /**
     *Обновление  группы
     */
    void updateGroup(String nameGroup) throws IOException;

    /**
     *Смотрим есть группа в списке
     */
    boolean existGroups(String nameGroup);
}
