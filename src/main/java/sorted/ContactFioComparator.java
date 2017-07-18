package sorted;

import models.Contact;
import java.io.Serializable;
import java.util.Comparator;

/**
 *Сортировка для контакта
 */
public class ContactFioComparator implements Comparator<Contact>,Serializable {
    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getFio().compareToIgnoreCase(o2.getFio());
    }
}
