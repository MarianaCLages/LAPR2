package app.ui.console;

import app.controller.ClinicalAnalysisLabController;
import app.controller.ParameterController;
import app.domain.model.ClinicalAnalysisLab;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.ui.console.utils.Utils;

public class ClinicalAnalysisLabUI implements Runnable {

    private ClinicalAnalysisLabController ctrl;

    public ClinicalAnalysisLabUI() {
        this.ctrl = new ClinicalAnalysisLabController();
    }


    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        boolean cont = true;
        if (ctrl.getTypetestList() == null || ctrl.getTypetestList().isEmpty()) {
            System.out.println("There are no TestTypes added to the system please add at least one before trying to create a new Clinical Analysis Lab");
        } else {
            do {
                boolean exception = false;
                do {
                    try {
                        String name = Utils.readLineFromConsole("Please enter the name of the new Clinical Analysis Lab");
                        String address = Utils.readLineFromConsole("Please enter the address of the new Clinical Analysis Lab");
                        String id = Utils.readLineFromConsole("Please enter the id of the new Clinical Analysis Lab");
                        String tin = Utils.readLineFromConsole("Please enter the tin of the new Clinical Analysis Lab");
                        String phoneNumber = Utils.readLineFromConsole("Please enter the phoneNumber of the new Clinical Analysis Lab");
                        TestType testType = (TestType) Utils.showAndSelectOne(ctrl.getTypetestList().getList(), "Select a TypeTest");

                        ctrl.createClinicalAnalysisLab(name,address,id,tin,phoneNumber,testType);
                        exception = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("An error occurred during the creation during the creation of the Clinical Analysis Lab please try again");
                        exception = true;
                    }
                } while (exception);

                cont = Utils.confirm("The following Clinical Analysis Lab was created do you want to save? " + ctrl.getcal().toString());
                if (cont) {

                    if (ctrl.saveClinicalAnalysisLab()) {
                        System.out.println("The Clinical Analysis Lab was saved with success");
                    }

                } else {
                    System.out.println("Couldn't save the Clinical Analysis Lab please try again ");
                }


            } while (!cont);

        }
    }
}