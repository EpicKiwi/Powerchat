package fr.epickiwi.powerchat.client.controller;

import fr.epickiwi.powerchat.client.model.Model;
import fr.epickiwi.powerchat.client.view.GuiView;
import fr.epickiwi.powerchat.client.view.SocketView;

public abstract class Controller {

    protected GuiView guiView;
    protected SocketView socketView;
    protected Model model;

    public Controller(GuiView guiView, SocketView socketView, Model model) {
        this.guiView = guiView;
        this.socketView = socketView;
        this.model = model;
    }

    public abstract void start();
}
