package app.ui.console;

import app.controller.ValidateController;
import app.domain.mappers.dto.TestStoreDTO;
import app.domain.model.Test;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ValidateUI implements Runnable{
    ValidateController ctr;

    public ValidateUI() {
        this.ctr = new ValidateController();
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
        boolean leave = false;
        if (this.ctr.getListOfTests() == null) {
            System.out.println("There are no tests to validate.");
        } else {
            List<Test> tSt = new ArrayList<Test>();
            List<TestStoreDTO> tListDTO = ctr.getListOfTests();
            do {
                Test test = (Test) Utils.showAndSelectOne(tListDTO, "Select a test to validate.");
                tSt.add(test);
                leave = !Utils.confirm("Do you want to validate more tests? (s/n)");
            } while (!leave);
            this.ctr.validateListOfTests(tSt);
            System.out.println("All the tests were validated with success");
        }
    }
}

