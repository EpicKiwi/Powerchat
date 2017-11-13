package fr.epickiwi.powerchat.com.message;

import fr.epickiwi.powerchat.com.user.ServerUser;
import fr.epickiwi.powerchat.com.user.User;

public class UserQuitMessage extends Message {

    private User quittedUser;

    public UserQuitMessage(User quittedUser) {
        super(ServerUser.getInstance());
        this.quittedUser = quittedUser;
    }

    @Override
    public String toString() {
        return "UserQuitMessage{" +
                "quittedUser=" + quittedUser +
                ", author=" + author +
                ", date=" + date +
                ", uuid=" + uuid +
                '}';
    }

    public User getQuittedUser() {
        return quittedUser;
    }

    public void setQuittedUser(User quittedUser) {
        this.quittedUser = quittedUser;
    }
}
