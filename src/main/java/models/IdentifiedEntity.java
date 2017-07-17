package models;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Общий класс для группы и контакта
 */
public abstract class IdentifiedEntity implements Serializable {

    private int id;
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    public IdentifiedEntity() {
        this.id = ATOMIC_INTEGER.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "id= " + id +
                '}';
    }
}
