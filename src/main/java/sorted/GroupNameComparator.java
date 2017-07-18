package sorted;

import models.Group;

import java.io.Serializable;
import java.util.Comparator;

/**
 *Сортировка для группы
 */
public class GroupNameComparator implements Comparator<Group>,Serializable {
    @Override
    public int compare(Group o1, Group o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
