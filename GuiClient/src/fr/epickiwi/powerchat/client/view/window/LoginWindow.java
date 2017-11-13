package fr.epickiwi.powerchat.client.view.window;

import fr.epickiwi.powerchat.client.view.GuiView;
import javafx.stage.Stage;

public class LoginWindow extends AppWindow {

    public LoginWindow(GuiView guiView) {
        super(guiView, new Stage(), "../../gui/login.fxml", 315, 119);
        this.stage.setResizable(false);
        this.stage.setTitle("Connection - Powerchat");
    }

}
