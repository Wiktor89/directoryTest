package models;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Общий класс для группы контакта, пользователя
 */
public abstract class IdentifiedEntity implements Serializable,Entity {

    private int id;

    public IdentifiedEntity() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
