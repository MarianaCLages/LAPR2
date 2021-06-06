package app.ui.gui.Client;

import app.ui.gui.Controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ClientUI {


    private SceneController sceneController=SceneController.getInstance();

    @FXML
    private Button myReturnButtonClient;

    public void returnToMenu(ActionEvent event) {
        try {
            sceneController.switchMenu(event, "/FXML/MainScreen.fxml");
        } catch (IOException e) {

        }
    }

}
