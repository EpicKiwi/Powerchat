package fr.epickiwi.powerchat.protocol.user;

import java.util.UUID;

public class ServerUser extends User {

    private static final ServerUser server = new ServerUser();
    private static final String SERVER_USERNAME = "Server";

    public static ServerUser getInstance(){
        return server;
    }

    private ServerUser() {
        super(SERVER_USERNAME);
    }
}
