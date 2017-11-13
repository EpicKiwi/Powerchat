package fr.epickiwi.powerchat.client;

import fr.epickiwi.powerchat.client.controller.MainController;
import fr.epickiwi.powerchat.client.model.Model;
import fr.epickiwi.powerchat.client.view.GuiView;
import fr.epickiwi.powerchat.client.view.SocketView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
            launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        GuiView guiView = new GuiView(model,stage);
        SocketView socketView = new SocketView(model);
        MainController controller = new MainController(guiView,socketView,model);
        guiView.setController(controller);
        socketView.setController(controller);
        controller.start();
    }
}
