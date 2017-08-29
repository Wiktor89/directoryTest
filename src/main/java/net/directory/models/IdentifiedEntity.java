package net.directory.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 *Общий класс для группы контакта, пользователя
 */
@MappedSuperclass
public abstract class IdentifiedEntity implements Serializable,Entity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    public IdentifiedEntity() {
    }
    
    public IdentifiedEntity(int id) {
        this.id = id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
