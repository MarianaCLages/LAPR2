package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClinicalChemistryTechnologistUI implements Runnable {
    public ClinicalChemistryTechnologistUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Record results", new RecordResultsUI()));
        options.add(new MenuItem("Consult client historical tests and check its details/results", new ConsultClientTestsAndResultsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
