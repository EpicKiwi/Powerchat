package fr.epickiwi.powerchat.client.gui;

import fr.epickiwi.powerchat.client.view.View;

public abstract class ViewController {

    protected View view;

    public void setView(View view) {
        this.view = view;
    }

}