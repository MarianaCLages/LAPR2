package app.ui.gui.LabCord;

import app.controller.App;
import app.controller.CheckPerformanceController;
import app.controller.SceneController;
import app.domain.shared.Constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckPerformanceUI implements Initializable {

    @FXML
    private ChoiceBox<String> myChoiceBoxSimple;
    @FXML
    private DatePicker dtnBeg;
    @FXML
    private DatePicker dtnEnd;

    private final SceneController sceneController = SceneController.getInstance();
    private final CheckPerformanceController ctrl = new CheckPerformanceController();
    private final App app = sceneController.getApp();

    public void returnToMenu(ActionEvent actionEvent) {
        app.doLogout();
        sceneController.switchMenu(actionEvent, Constants.LABORATORY_COORDINATOR_UI);
    }

    public void confirm(ActionEvent actionEvent) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ctrl.getSubArray(dtnBeg.getValue(), dtnEnd.getValue(),myChoiceBoxSimple.getValue());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choices = {Constants.BENCHMARK_ALGORITHM, Constants.BRUTEFORCE_ALGORITHM};
        myChoiceBoxSimple.getItems().addAll(choices);
    }
}
