package fr.epickiwi.powerchat.com.message;

import fr.epickiwi.powerchat.com.user.ServerUser;
import fr.epickiwi.powerchat.com.user.User;

public class UserChangeMessage extends Message {

    private User user;

    public UserChangeMessage(User user) {
        super(ServerUser.getInstance());
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserChangeMessage{" +
                "user=" + user +
                ", author=" + author +
                ", date=" + date +
                ", uuid=" + uuid +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
