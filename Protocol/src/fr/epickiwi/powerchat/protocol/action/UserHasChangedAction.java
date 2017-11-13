package fr.epickiwi.powerchat.protocol.action;

import fr.epickiwi.powerchat.protocol.user.User;

public class UserHasChangedAction extends ChatAction {

    private final User lastUser;
    private final User newUser;

    public UserHasChangedAction(User author,User lastUser , User newUser) {
        super(author);
        this.newUser = newUser;
        this.lastUser = lastUser;
    }

    public User getNewUser() {
        return newUser;
    }

    public User getLastUser() {
        return lastUser;
    }
}
