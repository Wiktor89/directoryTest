package models;

import java.io.Serializable;
import java.util.UUID;

/**
 *Общий класс для группы и контакта
 */
public abstract class IdentifiedEntity implements Serializable {

    private UUID id;
    private final static UUID UID = UUID.randomUUID();

    public IdentifiedEntity() {
        this.id = UID;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
