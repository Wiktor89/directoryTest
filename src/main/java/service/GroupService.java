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
    void listGroup() throws Exception;
    void addGroup(String nameGroup) throws IOException;
    void removeGroup(String nameGroup);
    void updateGroup(String nameGroup) throws IOException;
    boolean existGroups(String nameGroup);
}
