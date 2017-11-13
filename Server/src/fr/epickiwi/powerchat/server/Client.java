package fr.epickiwi.powerchat.server;

import fr.epickiwi.powerchat.log.Logger;
import fr.epickiwi.powerchat.protocol.action.*;
import fr.epickiwi.powerchat.protocol.user.ServerUser;
import fr.epickiwi.powerchat.protocol.user.User;
import fr.epickiwi.powerchat.server.controller.ChangeUserController;
import fr.epickiwi.powerchat.server.controller.MessageController;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Client implements Runnable {

    private User user;
    private final Socket socket;
    private final Server server;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private boolean connected;

    public Client(User user, Socket socket, Server server) throws IOException {
        this.user = user;
        this.socket = socket;
        this.server = server;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.connected = true;
    }

    @Override
    public void run() {
        Logger.info("Connexion du client "+this.user.getUsername());
        try {

            this.sendAction(new ChangeUserAction(ServerUser.getInstance(),this.user));
            this.server.sendBroadcast(new UserJoinAction(ServerUser.getInstance(),this.user),this);

            Object rawAction = in.readObject();
            while (rawAction != null){
                ChatAction action = (ChatAction) rawAction;

                if(action instanceof ChangeUserAction){
                    new ChangeUserController(this, (ChangeUserAction) action).run();
                } else if(action instanceof MessageAction) {
                    new MessageController(this, (MessageAction) action).run();
                } else {
                    Logger.log(action.toString());
                }

                rawAction = in.readObject();
            }
            this.connected = false;

        } catch (EOFException|SocketException e) {
            Logger.info("Client "+this.user.getUsername()+" déconnecté");
        }  catch (IOException e) {
            Logger.exception(e);
        } catch (ClassNotFoundException e) {
            Logger.error("Type de message inconnu");
            Logger.exception(e);
        }
        this.server.removeClient(this);
        this.server.sendBroadcast(new UserQuitAction(ServerUser.getInstance(),this.user),this);
    }

    public synchronized void sendAction(ChatAction action) {
        try {
            if (this.connected)
                this.out.writeObject(action);
        } catch (SocketException e) {
            Logger.warning("Le client de "+this.user.getUsername()+" est déconnecté");
        } catch (IOException e) {
            Logger.exception(e);
        }
    }

    //GETTERS & SETTERS


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Socket getSocket() {
        return socket;
    }

    public Server getServer() {
        return server;
    }
}
