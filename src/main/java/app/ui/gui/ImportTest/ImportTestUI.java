package app.ui.gui.ImportTest;

import app.controller.App;
import app.controller.ImportTestsController;
import app.domain.shared.Constants;
import app.ui.gui.controllers.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportTestUI implements Initializable {
    ImportTestsController imp = new ImportTestsController();


    @FXML
    private TextField textField;

    @FXML
    private Button confirm;

    @FXML
    private ListView<String> ListView;

    public ImportTestUI() throws IOException {
    }
    private SceneController sceneController = SceneController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListView.setVisible(false);

    }

    public void confirm() {
        imp.readTestsfromFile(textField.getText());
        ListView.setVisible(true);
        ListView.getItems().addAll(imp.saveFileTestList());
    }

    public void returnToMenu(ActionEvent event) {
        App app = sceneController.getApp();
        sceneController.switchMenu(event, Constants.LABORATORY_COORDINATOR_UI);
    }




}
