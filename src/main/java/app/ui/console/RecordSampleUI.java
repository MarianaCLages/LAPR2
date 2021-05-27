package app.ui.console;


import app.controller.RecordSampleController;
import app.domain.mappers.dto.TestDTO;
import app.domain.model.SampleStore;
import app.ui.console.utils.Utils;

import java.util.Scanner;

public class RecordSampleUI implements Runnable {


    private RecordSampleController ctrl;
    private TestDTO test;
    private SampleStore SampleList;


    public RecordSampleUI() {
        this.ctrl = new RecordSampleController();
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
            ctrl.getLists();
            boolean exception = true;
            do {

                this.test = (TestDTO) Utils.showAndSelectOne(ctrl.tList(), "Please select one Test");
                if (this.test == null) {
                    System.out.println("You must select one!");

                } else {
                    try {
                        int i = Utils.readIntegerFromConsole("How many samples do you want to record?");

                        for (int x = 0; x < i; x++) {
                            ctrl.createSample(this.test.getTestCode());
                            ctrl.saveSample();

                        }
                        exception = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("An error occurred during the creation during the creation of the Sample please try again");
                        exception = true;
                    }
                }
            } while (exception);

            cont = Utils.confirm("The Sample(s) was(were) recorded. Do you want to save? (s/n) \n" );
            if (cont){
                ctrl.confirm(this.test.getTestCode());
            }

        } while (!cont);

    }
}









