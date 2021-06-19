package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ParameterCategoryUI {

    @FXML
    private Button myReturnButtonParameterCategory;

    private SceneController sceneController = SceneController.getInstance();
    private App app = sceneController.getApp();


    public void returnToAdminMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.ADMINISTRATOR_UI);
    }

    public void runUI(){
        ParameterCategoryUI ui = new ParameterCategoryUI();

    }

}
