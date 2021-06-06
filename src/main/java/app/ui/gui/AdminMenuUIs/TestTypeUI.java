package app.ui.gui.AdminMenuUIs;

import app.ui.gui.Controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class TestTypeUI {

    public Button myReturnButtonTestType;
    private SceneController sceneController = SceneController.getInstance();


    public void returnToAdminMenu(ActionEvent event){
        try {
            sceneController.switchMenu(event, "/FXML/AdministratorUI.fxml");
        }catch (IOException e ){

        }
    }

}
