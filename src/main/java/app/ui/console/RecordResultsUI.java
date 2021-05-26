package app.ui.console;

import app.controller.RecordResultsController;
import app.domain.mappers.dto.TestParameterDTO;
import app.ui.console.utils.Utils;

public class RecordResultsUI implements Runnable {
    RecordResultsController ctr;

    public RecordResultsUI() {
        this.ctr = new RecordResultsController();
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
        String barcode = Utils.readLineFromConsole("Please enter the barcode number of the sample of the test to register results");
        try {


            for (TestParameterDTO tp : ctr.getTestParameterList(barcode)) {
                System.out.println(tp.toString());
                double result = Utils.readDoubleFromConsole("Plese insert the result");

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
