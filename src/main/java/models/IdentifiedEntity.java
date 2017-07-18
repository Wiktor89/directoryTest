package models;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Общий класс для группы и контакта
 */
public abstract class IdentifiedEntity implements Serializable {

    private int id;


    public IdentifiedEntity() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "id = " + id;
    }
}
