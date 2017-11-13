package fr.epickiwi.powerchat.com.message;

import fr.epickiwi.powerchat.com.user.User;

public class TextMessage extends Message {

    private String message;

    public TextMessage(User author, String message) {
        super(author);
        this.message = message;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "message='" + message + '\'' +
                ", author=" + author +
                ", date=" + date +
                ", uuid=" + uuid +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
