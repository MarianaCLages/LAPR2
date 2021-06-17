package app.ui.gui.LabCord;

import app.controller.App;
import app.domain.shared.Constants;
import app.ui.gui.controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class CompanyPerformanceUI implements Initializable {
    public TextArea myTextAreaSimple;
    public ChoiceBox<String> myChoiceBoxSimple;
    private SceneController sceneController = SceneController.getInstance();


    @FXML
    private Button btnReturn;

    public void returnToMenu(ActionEvent actionEvent) {
        App app = sceneController.getApp();
        app.doLogout();
        sceneController.switchMenu(actionEvent, Constants.LABORATORY_COORDINATOR_UI);
    }

    public void confirm(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {Constants.BENCHMARK_ALGORITHM, Constants.BRUTEFORCE_ALGORITHM};
        myChoiceBoxSimple.getItems().addAll(choices);
    }
}
