package service;

import java.io.IOException;

/**
 *Интерфейс для группы
 */
public interface GroupService {

    /**
     * Все что к группе
     */
    void listGroupContact(String nameGroup);
    void listGroup();
    void addGroup(String nameGroup) throws IOException;
    void removeGroup(String nameGroup);
    void updateGroup(String nameGroup);
    boolean existGroups(String nameGroup);
}
