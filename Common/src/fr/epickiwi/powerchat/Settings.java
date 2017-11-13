package fr.epickiwi.powerchat;

public class Settings {
    private static Settings ourInstance = new Settings();

    public static Settings getInstance() {
        return ourInstance;
    }

    private int serverPort;

    private Settings() {
        this.serverPort = 7600;
    }

    public int getServerPort() {
        return serverPort;
    }
}
