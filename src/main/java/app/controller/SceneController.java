package app.controller;

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

    private final CheckPerformanceController ctrl = new CheckPerformanceController();
    private Runnable ui;

    /**
     * Gets the Check Performance Controller.
     *
     * @return the Check Performance Controller
     */
    public CheckPerformanceController getCtrl() {
        return ctrl;
    }

    /**
     * Gets the instance.
     *
     * @return the singleton (Scene Controller)
     */
    public static SceneController getInstance() {

        if (singleton == null) {
            synchronized (SceneController.class) {
                singleton = new SceneController();
            }
        }
        return singleton;
    }

    /**
     * Gets the auth controller.
     *
     * @return the auth controller
     */
    public AuthController getAuthController() {
        return authController;
    }

    /**
     * Gets the App.
     *
     * @return the app
     */
    public App getApp() {
        return app;
    }

    /**
     * Switches the menu.
     *
     * @param event the event
     * @param path  the path
     */
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

    /**
     * Gets the company.
     *
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Gets the graphical user interface (UI).
     *
     * @return the graphical user interface
     */
    public Runnable getUi() {
        return ui;
    }
}
