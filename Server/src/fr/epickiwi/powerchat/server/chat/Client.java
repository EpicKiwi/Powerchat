package fr.epickiwi.powerchat.server.chat;

import fr.epickiwi.powerchat.com.message.*;
import fr.epickiwi.powerchat.com.user.AnonymousUser;
import fr.epickiwi.powerchat.com.user.User;
import fr.epickiwi.powerchat.log.Logger;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable{

    private User user;
    private Socket socket;
    private boolean connected;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private ClientPool pool;
    private Thread thread;

    public Client(int userId, Socket socket, ClientPool pool) throws IOException {
        this.user = new AnonymousUser(userId);
        this.socket = socket;
        this.connected = true;
        this.input = new ObjectInputStream(this.socket.getInputStream());
        this.output = new ObjectOutputStream(this.socket.getOutputStream());
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            Logger.info("Client "+this.user.getName()+" connecté");
            this.pool.sendBroadcast(new UserJoinMessage(this.getUser()));
            this.sendMessage(new UserChangeMessage(this.user));

            try {
                Object rawMessage = this.input.readObject();
                while (rawMessage != null) {
                    Message message = (Message) rawMessage;
                    Logger.info(this.user.getName() + " : " + message.toString());
                    dispatchMessage(message);
                    rawMessage = this.input.readObject();
                }
            } catch (EOFException e){
                Logger.error("Fin du flux");
            }

            this.socket.close();
            this.connected = false;
            Logger.info("Client " + this.user.getName() + " déconnecté");
            this.pool.sendBroadcast(new UserQuitMessage(this.user));

        } catch (IOException e) {
            this.connected = false;
            Logger.exception(e);
        } catch (ClassNotFoundException e) {
            Logger.error("Type de message inconnu");
            Logger.exception(e);
        }
    }

    private void dispatchMessage(Message message) throws IOException {
        if(message instanceof TextMessage){
            this.pool.sendBroadcast(new TextMessage(this.user,((TextMessage) message).getMessage()));
        } else {
            Logger.warning("Message de type inconnu");
        }
    }

    public void sendMessage(Message message) throws IOException {
        this.output.writeObject(message);
        this.output.flush();
    }

    public boolean isConnected() {
        return connected;
    }

    public void close() throws IOException {
        this.connected = false;
        this.thread.interrupt();
        this.output.close();
        this.input.close();
        this.socket.close();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
