package app.ui.gui.clichetecMenuUIs;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RecordResultsUI {

    @FXML
    public TextField sampleIDtxt;

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();

    public void btnReturnAction(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.CLINICAL_CHEMISTRY_TECHNOLOGIST_UI);
    }
}
