package fr.epickiwi.powerchat.client.model;

import javafx.beans.property.*;

import java.net.Socket;

public class ConnectionModel {

    private StringProperty connectionHost;
    private StringProperty connectionUsername;
    private ObjectProperty<Socket> serverConnection;
    private ObjectProperty<ConnectionState> connectionState;
    private IntegerProperty connectionPort;

    public ConnectionModel() {
        this.serverConnection = new SimpleObjectProperty<>();
        this.connectionState = new SimpleObjectProperty<>(ConnectionState.NOT_CONNECTED);
        this.connectionHost = new SimpleStringProperty();
        this.connectionUsername = new SimpleStringProperty();
        this.connectionPort = new SimpleIntegerProperty(7600);
    }

    public Socket getServerConnection() {
        return serverConnection.get();
    }

    public ObjectProperty<Socket> serverConnectionProperty() {
        return serverConnection;
    }

    public void setServerConnection(Socket serverConnection) {
        this.serverConnection.set(serverConnection);
    }

    public ConnectionState getConnectionState() {
        return connectionState.get();
    }

    public ObjectProperty<ConnectionState> connectionStateProperty() {
        return connectionState;
    }

    public void setConnectionState(ConnectionState connectionState) {
        this.connectionState.set(connectionState);
    }

    public String getConnectionHost() {
        return connectionHost.get();
    }

    public StringProperty connectionHostProperty() {
        return connectionHost;
    }

    public void setConnectionHost(String connectionHost) {
        this.connectionHost.set(connectionHost);
    }

    public String getConnectionUsername() {
        return connectionUsername.get();
    }

    public StringProperty connectionUsernameProperty() {
        return connectionUsername;
    }

    public void setConnectionUsername(String connectionUsername) {
        this.connectionUsername.set(connectionUsername);
    }

    public int getConnectionPort() {
        return connectionPort.get();
    }

    public IntegerProperty connectionPortProperty() {
        return connectionPort;
    }

    public void setConnectionPort(int connectionPort) {
        this.connectionPort.set(connectionPort);
    }
}
