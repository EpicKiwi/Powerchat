package fr.epickiwi.powerchat.client.view;

import fr.epickiwi.powerchat.client.controller.MainController;
import fr.epickiwi.powerchat.client.model.Model;

public abstract class View {

    protected Model model;
    protected MainController controller;

    public View(Model model) {
        this.model = model;
    }

    public abstract void setupListeners();

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public MainController getController() {
        return controller;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}
