package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable {
    public AdminUI() {
        //UI constructor
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create a new Parameter Category", new ParameterCategoryUI()));
        options.add(new MenuItem("Create a new Parameter", new ParameterUI()));
        options.add(new MenuItem("Register an new Type of Test ", new TestTypeUI()));
        options.add(new MenuItem("Register an Clinical Analysis Lab ", new ClinicalAnalysisLabUI()));
        options.add(new MenuItem("Register an Employee ", new RegisterEmployeeUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
