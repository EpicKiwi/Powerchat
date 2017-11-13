package fr.epickiwi.powerchat.client.controller;

import fr.epickiwi.powerchat.client.model.ConnectionModel;
import fr.epickiwi.powerchat.client.model.ConnectionState;
import fr.epickiwi.powerchat.client.model.Model;
import fr.epickiwi.powerchat.client.view.GuiView;
import fr.epickiwi.powerchat.client.view.SocketView;
import fr.epickiwi.powerchat.log.Logger;

import java.io.IOException;
import java.net.Socket;

public class MainController extends Controller{

    public MainController(GuiView guiView, SocketView socketView, Model model) {
        super(guiView, socketView, model);
    }

    @Override
    public void start() {
        this.guiView.setupListeners();
        this.socketView.setupListeners();

        this.guiView.showLoginWindow();
    }

    public void connectToServer() {
        ConnectionModel coModel = this.model.getConnectionModel();
        coModel.setConnectionState(ConnectionState.CONNECTING);
        Socket connectSocket = null;
        try {
            connectSocket = new Socket(coModel.getConnectionHost(),7600);
        } catch (IOException e) {
            Logger.exception(e);
            coModel.setConnectionState(ConnectionState.CONNECTION_ERROR);
            return;
        }
        coModel.setServerConnection(connectSocket);
        coModel.setConnectionState(ConnectionState.CONNECTED);
    }
}
