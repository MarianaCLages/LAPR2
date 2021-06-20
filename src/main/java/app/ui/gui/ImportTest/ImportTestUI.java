package app.ui.gui.ImportTest;

import app.controller.ImportTestsController;
import app.domain.shared.Constants;
import app.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportTestUI implements Initializable {

    private final ImportTestsController imp = new ImportTestsController();
    private final SceneController sceneController = SceneController.getInstance();

    @FXML
    private ListView<String> ListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListView.setVisible(false);
    }

    public void confirm() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
        File f = chooser.showOpenDialog(null);

        imp.readTestsfromFile(f.getAbsolutePath());
        ListView.setVisible(true);
        ListView.getItems().addAll(imp.saveFileTestList());
    }

    public void returnToMenu(ActionEvent event) {
        sceneController.switchMenu(event, Constants.LABORATORY_COORDINATOR_UI);
    }
}
