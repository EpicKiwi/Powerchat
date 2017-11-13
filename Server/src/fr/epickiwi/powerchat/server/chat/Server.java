package fr.epickiwi.powerchat.server.chat;

import fr.epickiwi.powerchat.log.Logger;
import fr.epickiwi.powerchat.Settings;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread {

    private static Server ourInstance = new Server();

    public static Server getInstance() {
        return ourInstance;
    }

    private ServerSocket server;
    private ClientPool clients;
    private boolean listening;
    private int port;

    private Server() {
        this.clients = new ClientPool();
        this.port = Settings.getInstance().getServerPort();
        this.listening = true;
    }

    @Override
    public void run() {
        Logger.info("Démarrage du serveur");
        try {
            Logger.info("Écoute sur *:"+this.port);
            this.server = new ServerSocket(this.port);

            while(this.listening) {
                this.clients.connectClient(this.server.accept());
            }

        } catch (IOException e) {
            this.close();
            Logger.exception(e);
        }
    }

    public void close(){
        this.listening = false;
        try {
            Logger.info("Fermeture du serveur");
            this.clients.disconnectClients();
            if(this.server != null)
                this.server.close();
        } catch (IOException e1) {
            Logger.exception(e1);
        }
    }

    public ClientPool getClients() {
        return clients;
    }

    public void setClients(ClientPool clients) {
        this.clients = clients;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isListening() {
        return listening;
    }

    public void setListening(boolean listening) {
        this.listening = listening;
    }
}
