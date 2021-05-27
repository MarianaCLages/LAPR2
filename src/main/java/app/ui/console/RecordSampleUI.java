
package app.ui.console;


import app.controller.RecordSampleController;
import app.domain.mappers.dto.TestDTO;
import app.domain.model.SampleStore;
import app.ui.console.utils.Utils;

import java.util.Scanner;

public class RecordSampleUI implements Runnable {

    Scanner scanner = new Scanner(System.in);
    private final RecordSampleController ctrl;
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
                boolean exception = false;
                do {
                    try {
                        this.test = (TestDTO) Utils.showAndSelectOne(ctrl.tList(), "Please select one Test");
                        System.out.println("How many samples do you want to record?");
                        int i = scanner.nextInt();

                        for (int x = 0; x < i; x++) {
                            ctrl.createSample(this.test.getTestCode());

                        }
                        exception = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("An error occurred during the creation during the creation of the Sample please try again");
                        exception = true;
                    }
                } while (exception);

                cont = Utils.confirm("The following Sample was recorded do you want to save? (s/n) \n" + ctrl.getSample());
                if (cont) {

                    if (ctrl.saveSample()) {
                        System.out.println("The Sample was saved with success");
                    } else {
                        System.out.println("Couldn't save the Sample please try again ");
                    }
                }

            } while (!cont);

        }
    }









