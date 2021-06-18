package app.ui.gui.adminMenuUIs;

import app.controller.App;
import app.controller.GenerateNHSReportController;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class MultiLinearRegressionUI implements Initializable {

    @FXML
    private TextArea myTextAreaMulti;
    @FXML
    private Button myReturnButtonMulti;

    private SceneController sceneController = SceneController.getInstance();
    private App app = sceneController.getApp();
    private GenerateNHSReportController ctrl = SceneController.getInstance().getCtrl();

    public void returnToGenerateNHSReport(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.NHS_REPORT_UI);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ctrl.multiRegression();
        myTextAreaMulti.setText(ctrl.getSb().toString());
    }
}
