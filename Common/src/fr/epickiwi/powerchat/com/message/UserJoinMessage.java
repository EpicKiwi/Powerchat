package fr.epickiwi.powerchat.com.message;

import fr.epickiwi.powerchat.com.user.ServerUser;
import fr.epickiwi.powerchat.com.user.User;

public class UserJoinMessage extends Message {

    private User joinedUser;

    public UserJoinMessage(User joinedUser) {
        super(ServerUser.getInstance());
        this.joinedUser = joinedUser;
    }

    @Override
    public String toString() {
        return "UserJoinMessage{" +
                "joinedUser=" + joinedUser +
                ", author=" + author +
                ", date=" + date +
                ", uuid=" + uuid +
                '}';
    }

    public User getJoinedUser() {
        return joinedUser;
    }

    public void setJoinedUser(User joinedUser) {
        this.joinedUser = joinedUser;
    }
}
