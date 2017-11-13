package fr.epickiwi.powerchat.server.chat;

import fr.epickiwi.powerchat.com.message.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientPool {

    private ArrayList<Client> clients;
    private int clientIdentifier;

    public ClientPool() {
        this.clientIdentifier = 0;
        this.clients = new ArrayList<>();
    }

    public void connectClient(Socket socket) throws IOException {
        clientIdentifier++;
        Client client = new Client(clientIdentifier,socket,this);
        this.clients.add(client);
        Thread clientThread = new Thread(client);
        client.setThread(clientThread);
        clientThread.start();
    }

    public void sendBroadcast(Message message) throws IOException {
        for(Client client: this.clients){
            if(client.isConnected())
                client.sendMessage(message);
        }
    }

    public void disconnectClients() throws IOException {
        for(Client client: this.clients){
            if(client.isConnected())
                client.close();
        }
    }
}
