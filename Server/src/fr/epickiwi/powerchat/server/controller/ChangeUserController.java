package fr.epickiwi.powerchat.server.controller;

import fr.epickiwi.powerchat.log.Logger;
import fr.epickiwi.powerchat.protocol.action.ChangeUserAction;
import fr.epickiwi.powerchat.protocol.action.UserHasChangedAction;
import fr.epickiwi.powerchat.protocol.user.ServerUser;
import fr.epickiwi.powerchat.protocol.user.User;
import fr.epickiwi.powerchat.server.Client;

public class ChangeUserController extends Controller<ChangeUserAction> {

    public ChangeUserController(Client client, ChangeUserAction action) {
        super(client, action);
    }

    @Override
    public void run() {
        User lastUser = this.client.getUser();
        User newUser = this.action.getNewUser();
        Logger.info(lastUser.getUsername()+" change d'utilisateur pour "+newUser.getUsername());
        this.client.setUser(newUser);
        this.client.sendAction(new ChangeUserAction(ServerUser.getInstance(),newUser));
        this.client.getServer().sendBroadcast(new UserHasChangedAction(ServerUser.getInstance(),lastUser,newUser),this.client);
    }
}
