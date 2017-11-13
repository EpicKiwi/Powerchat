package fr.epickiwi.powerchat.protocol.action;

import fr.epickiwi.powerchat.protocol.user.User;

public class UserJoinAction extends ChatAction {

    private final User user;

    public UserJoinAction(User author, User user) {
        super(author);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
