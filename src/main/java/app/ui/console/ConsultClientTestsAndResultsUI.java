package app.ui.console;

import app.controller.ConsultClientTestsAndResultsController;
import app.domain.model.Client;
import app.domain.model.Test;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ConsultClientTestsAndResultsUI implements Runnable {
    ConsultClientTestsAndResultsController ctrl;

    public ConsultClientTestsAndResultsUI() {
        ctrl = new ConsultClientTestsAndResultsController();
    }

    public void run() {
        try {
            ctrl.getClientListTin();
        } catch (Exception e) {
            System.out.println("Client List empty!!");
            return;
        }

        List<String> clientTin = new ArrayList<>();
        String tin;

        for (Client client : ctrl.getClientListTin()) {
            clientTin.add(client.getTinNumber());
        }

        tin = Utils.showAndSelectOne(clientTin, "\nClient list ordered by TIN number:").toString();

        List<Test> testList;
        List<String> testTin = new ArrayList<>();
        ctrl.selectedClient(tin);

        try {
            testList = ctrl.getValidatedTestList();
        } catch (Exception e) {
            System.out.println("ERROR TEST!");
            return;
        }

        for (Test test : testList) {
            testTin.add(test.getTestNhsNumber());
        }

        tin = Utils.showAndSelectOne(testTin, "Test List").toString();

        try {
            Test test = ctrl.selectedTest(tin, testList);
            System.out.println(ctrl.toString(test));
        } catch (NullPointerException e) {
            System.out.println("ERROR FINAL!!");
        }
    }
}
