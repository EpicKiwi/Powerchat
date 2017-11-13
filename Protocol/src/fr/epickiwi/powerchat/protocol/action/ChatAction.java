package fr.epickiwi.powerchat.protocol.action;

import fr.epickiwi.powerchat.protocol.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class ChatAction implements Serializable {

    private User author;
    private Date date;
    private final UUID id;

    public ChatAction(User author) {
        this.author = author;
        this.date = new Date();
        this.id = UUID.randomUUID();
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

    public UUID getId() {
        return id;
    }
}
