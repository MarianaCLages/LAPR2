package app.ui.console;

import app.controller.ClinicalAnalysisLabController;
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
        boolean again = false;
        if (ctrl.getTypetestList() == null || ctrl.getTypetestList().isEmpty()) {
            System.out.println("There are no TestTypes added to the system please add at least one before trying to create a new Clinical Analysis Lab");
        } else {
            do {
                boolean exception = false;
                do {
                    try {
                        String name = Utils.readLineFromConsole("Please, enter the name of the new Clinical Analysis Lab:");
                        String address = Utils.readLineFromConsole("Please, enter the address of the new Clinical Analysis Lab:");
                        String id = Utils.readLineFromConsole("Please, enter the ID of the new Clinical Analysis Lab:");
                        String tin = Utils.readLineFromConsole("Please, enter the TIN of the new Clinical Analysis Lab:");
                        String phoneNumber = Utils.readLineFromConsole("Please, enter the phone number of the new Clinical Analysis Lab:");

                        do {

                            TestType testType = (TestType) Utils.showAndSelectOne(ctrl.getTypetestList().getList(), "Select a Test Type:");
                            ctrl.addToList(testType);

                            again = Utils.confirm("Do you want to add more tests? (s/n)");

                        } while (again);
                        ctrl.createClinicalAnalysisLab(name, address, id, tin, phoneNumber);
                        exception = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
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