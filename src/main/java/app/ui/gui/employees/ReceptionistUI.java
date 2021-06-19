package app.ui.gui.employees;

import app.controller.App;
import app.domain.shared.Constants;
import app.controller.SceneController;
import app.ui.console.CreateClientUI;
import app.ui.console.RegisterTestUI;
import javafx.event.ActionEvent;


public class ReceptionistUI {

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();
    private Runnable ui = sceneController.getUi();

    public void returnToMenu(ActionEvent event) {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }

    public void goToRegisterClientUI() {
        this.ui = new CreateClientUI();
        ui.run();
    }

    public void goToRegisterTestUI(){
        this.ui= new RegisterTestUI();
        ui.run();
    }

}
