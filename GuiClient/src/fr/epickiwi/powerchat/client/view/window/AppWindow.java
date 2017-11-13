package fr.epickiwi.powerchat.client.view.window;

import fr.epickiwi.powerchat.client.gui.ViewController;
import fr.epickiwi.powerchat.client.view.GuiView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppWindow {

    protected Scene rootScene;
    protected GuiView guiView;
    protected ViewController controller;
    protected Stage stage;

    public AppWindow(GuiView guiView, Stage stage, String fxmlLocation, int width, int height) {
        this.guiView = guiView;
        this.stage = stage;

        Parent rootNode = null;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlLocation));
            rootNode = loader.load();
            this.controller = loader.getController();
            this.controller.setView(this.guiView);
        } catch(IOException e){
            e.printStackTrace();
            return;
        }

        this.rootScene = new Scene(rootNode,width, height);

        this.stage.setScene(this.rootScene);
    }

    public Scene getRootScene() {
        return rootScene;
    }

    public GuiView getGuiView() {
        return guiView;
    }

    public ViewController getController() {
        return controller;
    }

    public Stage getStage() {
        return stage;
    }
}
