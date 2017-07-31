package models;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Общий класс для группы и контакта
 */
public abstract class IdentifiedEntity implements Serializable,Entity {

    private int id;
    private final static AtomicInteger UID = new AtomicInteger(0);

    public IdentifiedEntity() {
        this.id = UID.incrementAndGet();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
