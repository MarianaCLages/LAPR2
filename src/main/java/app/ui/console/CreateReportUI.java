package app.ui.console;

import app.controller.CreateReportController;
import app.domain.mappers.dto.TestDTO;
import app.ui.console.utils.Utils;

public class CreateReportUI implements Runnable {

    private CreateReportController ctrl;
    private TestDTO test;
    private String diagnosis;


    public CreateReportUI() {
        this.ctrl = new CreateReportController();
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
        do {
            boolean exception = true;
            do {

                this.test = (TestDTO) Utils.showAndSelectOne(ctrl.tList(), "Please select one Test");
                if (this.test == null) {
                    System.out.println("You must select one!");

                } else {
                    try {

                        System.out.println(ctrl.getResults(this.test.getTestCode()));
                        this.diagnosis = Utils.readLineFromConsole("Please write the report: ");

                        ctrl.createReport(diagnosis);

                        exception = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("An error occurred during the creation during the creation of the Sample please try again");
                        exception = true;
                    }
                }
            } while (exception);

            cont = Utils.confirm("This report were created do you want to save it? (s/n) \n");
            if (cont) {
                ctrl.saveReport();
                System.out.println("Saved!");
            }

        } while (!cont);

    }
}




