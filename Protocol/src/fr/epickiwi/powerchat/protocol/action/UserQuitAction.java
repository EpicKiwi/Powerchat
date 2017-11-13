package fr.epickiwi.powerchat.protocol.action;

import fr.epickiwi.powerchat.protocol.user.User;

public class UserQuitAction extends ChatAction {

    private final User user;

    public UserQuitAction(User author, User user) {
        super(author);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
