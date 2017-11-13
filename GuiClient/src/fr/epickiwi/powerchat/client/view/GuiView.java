package fr.epickiwi.powerchat.client.view;

import fr.epickiwi.powerchat.client.model.ConnectionState;
import fr.epickiwi.powerchat.client.model.Model;
import fr.epickiwi.powerchat.client.view.window.LoginWindow;
import fr.epickiwi.powerchat.client.view.window.MainWindow;
import javafx.beans.property.ObjectProperty;
import javafx.stage.Stage;

public class GuiView extends View {

    private LoginWindow loginWindow;
    private MainWindow mainWindow;

    public GuiView(Model model, Stage mainStage) {
        super(model);
        this.loginWindow = new LoginWindow(this);
        this.mainWindow = new MainWindow(this,mainStage);
    }

    public void showLoginWindow() {

        this.loginWindow.getStage().show();

    }

    @Override
    public void setupListeners() {
        this.model.getConnectionModel().connectionStateProperty().addListener((observableValue, aBoolean, t1) -> {
            this.onConnectionChange((ObjectProperty<ConnectionState>) observableValue,aBoolean,t1);
        });
    }

    public void onConnectionChange(ObjectProperty<ConnectionState> property, ConnectionState oldVal, ConnectionState newVal){
        if(newVal == ConnectionState.CONNECTED){
            this.loginWindow.getStage().hide();
            this.mainWindow.getStage().show();
        } else {
            this.mainWindow.getStage().hide();
            this.loginWindow.getStage().show();
        }
    }

}
