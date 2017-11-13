package fr.epickiwi.powerchat.server.controller;

import fr.epickiwi.powerchat.protocol.action.ChatAction;
import fr.epickiwi.powerchat.server.Client;

public abstract class Controller<T extends ChatAction> implements Runnable {

    protected Client client;
    protected T action;

    public Controller(Client client,T action) {
        this.client = client;
        this.action = action;
    }
}
