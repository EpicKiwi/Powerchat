package fr.epickiwi.powerchat.protocol.user;

import fr.epickiwi.powerchat.log.Logger;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable, Cloneable{

    protected String username;
    protected final UUID id;

    public User(String username) {
        this.username = username;
        this.id = UUID.randomUUID();
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            Logger.exception(e);
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getId() {
        return id;
    }
}
