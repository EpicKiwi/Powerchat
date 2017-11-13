package fr.epickiwi.powerchat.server;

import fr.epickiwi.powerchat.Settings;
import fr.epickiwi.powerchat.log.Logger;
import fr.epickiwi.powerchat.protocol.action.ChatAction;
import fr.epickiwi.powerchat.protocol.user.AnonymousUser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private ExecutorService clientPool;
    private ArrayList<Client> clients;
    private Thread serverThread;
    private boolean listening;
    private int anoDiscriminant;

    public Server() {
        Logger.info("Création du serveur");
        this.clientPool = Executors.newCachedThreadPool();
        this.listening = false;
        this.clients = new ArrayList<>();
        this.anoDiscriminant = 0;
    }

    public void stopListening(){
        this.listening = false;
    }

    @Override
    public void run() {
        Logger.info("Démarrage du serveur");
        try {
            this.serverSocket = new ServerSocket(Settings.getInstance().getServerPort());
            this.listening = true;

                Logger.info("Écoute sur *:"+this.serverSocket.getLocalPort());
            while(this.listening){
                Socket clientSocket = this.serverSocket.accept();
                Logger.info("Connexion entrante");
                this.anoDiscriminant++;
                Client client = new Client(new AnonymousUser(this.anoDiscriminant),clientSocket,this);
                this.clients.add(client);
                this.clientPool.submit(client);
            }

        } catch (IOException e) {
            Logger.fatalException(e);
        }
        this.listening = false;
        this.clientPool.shutdown();
        Logger.info("Arret du serveur");
    }

    public void sendBroadcast(ChatAction action, Client... exept){
        for(Client client:this.clients){
            boolean ignored = false;
            for(Client excepted: exept){
                if(excepted == client){
                    ignored = true;
                    break;
                }
            }
            if(!ignored)
                client.sendAction(action);
        }
    }

    // GETTERS & SETTERS

    public void removeClient(Client client){
        this.clients.remove(client);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public ExecutorService getClientPool() {
        return clientPool;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public boolean isListening() {
        return listening;
    }

    public int getAnoDiscriminant() {
        return anoDiscriminant;
    }

    public void setListening(boolean listening) {
        this.listening = listening;
    }
}
