package fr.epickiwi.powerchat.protocol.action;

import fr.epickiwi.powerchat.protocol.user.User;

public class ChangeUserAction extends ChatAction {

    private final User newUser;

    public ChangeUserAction(User author, User newUser) {
        super(author);
        this.newUser = newUser;
    }

    public User getNewUser() {
        return newUser;
    }
}
