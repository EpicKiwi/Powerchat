package fr.epickiwi.powerchat.server;

import fr.epickiwi.powerchat.server.chat.Server;

public class Main {
    public static void main(String[] args){

        Server chatServer = Server.getInstance();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            chatServer.close();
        }));

        chatServer.start();
    }
}
