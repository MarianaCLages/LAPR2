package app.ui.console;

import app.controller.ParameterController;
import app.domain.model.ParameterCategory;
import app.ui.console.utils.Utils;

import java.util.List;

public class ParameterUI implements Runnable {

    private ParameterController ctrl;

    public ParameterUI() {
        this.ctrl = new ParameterController();
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
        if (ctrl.getParameterCategoryList() == null || ctrl.getParameterCategoryList().isEmpty()) {
            System.out.println("There are no Categories added to the system please add at least one before trying to create a new Parameter");
        } else {
            do {
                boolean exception = false;
                do {
                    try {
                        String code = Utils.readLineFromConsole("Please enter the code of the new Parameter");
                        String name = Utils.readLineFromConsole("Please enter the name of the new Parameter");
                        String description = Utils.readLineFromConsole("Please enter the description of the new Parameter");

                        ParameterCategory cat = (ParameterCategory) Utils.showAndSelectOne(ctrl.getParameterCategoryList().getList(), "Select a Category");
                        ctrl.createParameter(code, name, description, cat);
                        exception = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("An error occurred during the creation during the creation of the Parameter please try again");
                        exception = true;
                    }
                } while (exception);

                cont = Utils.confirm("The following Parameter was created do you want to save? " + ctrl.getpc().toString());
                if (cont) {

                    if (ctrl.saveParameter()) {
                        System.out.println("The Parameter was saved with success");
                    }

                } else {
                    System.out.println("Couldn't save the Parameter please try again ");
                }


            } while (!cont);

        }
    }
}


