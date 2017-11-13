package fr.epickiwi.powerchat.client.gui;

import fr.epickiwi.powerchat.client.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Login extends ViewController {

    @FXML
    private TextField loginField;
    @FXML
    private TextField hostField;

    @FXML
    private void initialize(){
    }

    @Override
    public void setView(View view) {
        super.setView(view);
        loginField.textProperty().bindBidirectional(this.view.getModel().getConnectionModel().connectionUsernameProperty());
        hostField.textProperty().bindBidirectional(this.view.getModel().getConnectionModel().connectionHostProperty());
    }

    @FXML
    private void onConnectionHitted(){
        if(loginField.getText() == null || loginField.getText().trim().equals(""))
            return;
        if(hostField.getText() == null || hostField.getText().trim().equals(""))
            return;
        this.view.getController().connectToServer();
    }
}
