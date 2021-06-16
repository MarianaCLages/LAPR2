package app.ui.gui.controllers;

import app.controller.App;
import app.controller.AuthController;
import app.controller.GenerateNHSReportController;
import app.domain.model.Company;
import app.domain.shared.exceptions.MenuNotFoundException;
import app.ui.gui.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneController {

    private static SceneController singleton = null;
    private AuthController authController = new AuthController();
    private App app = App.getInstance();
    private Company company = app.getCompany();
    private GenerateNHSReportController ctrl = new GenerateNHSReportController();

    public static SceneController getInstance() {

        if (singleton == null) {
            synchronized (SceneController.class) {
                singleton = new SceneController();
            }
        }
        return singleton;
    }

    public AuthController getAuthController() {
        return authController;
    }

    public App getApp() {
        return app;
    }

    public void switchMenu(ActionEvent event, String path) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

            if (loader == null) {
                throw new MenuNotFoundException();
            }

            Parent root = loader.load();
            Scene scene = new Scene(root);
            //String css = this.getClass().getResource("/Styles/Styles.css").toExternalForm();
            //scene.getStylesheets().add(css);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException | MenuNotFoundException error) {
            Alerts.errorAlert(error.getMessage());
        }


    }

    public Company getCompany() {
        return company;
    }

    public GenerateNHSReportController getCtrl() {
        return ctrl;
    }
}
