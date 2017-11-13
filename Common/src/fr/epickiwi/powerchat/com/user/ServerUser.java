package fr.epickiwi.powerchat.com.user;

public class ServerUser extends User {
    private static ServerUser ourInstance = new ServerUser();

    public static ServerUser getInstance() {
        return ourInstance;
    }

    private ServerUser() {
        super("Serveur",0);
    }

    @Override
    public String toString() {
        return "ServerUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
