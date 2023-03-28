package entities;

import java.io.Serializable;

public class Entity implements Serializable {

    long id;

    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
