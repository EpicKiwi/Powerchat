package fr.epickiwi.powerchat.com.message;

import fr.epickiwi.powerchat.com.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class Message implements Serializable{

    protected User author;
    protected Date date;
    protected UUID uuid;

    public Message(User author) {
        this.author = author;
        this.date = new Date();
        this.uuid = UUID.randomUUID();
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
