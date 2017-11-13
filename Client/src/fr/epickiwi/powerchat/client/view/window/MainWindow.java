package fr.epickiwi.powerchat.client.view.window;

import fr.epickiwi.powerchat.client.view.GuiView;
import javafx.stage.Stage;

public class MainWindow extends AppWindow {

    public MainWindow(GuiView guiView, Stage mainStage) {
        super(guiView, mainStage, "../../gui/main.fxml", 409, 571);
        this.stage.setTitle("Powerchat");
    }

}
