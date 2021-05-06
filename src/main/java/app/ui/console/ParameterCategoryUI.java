package app.ui.console;

import app.controller.ParameterCategoryController;
import app.ui.console.utils.Utils;

public class ParameterCategoryUI implements Runnable {
    private ParameterCategoryController ctrl;

    public ParameterCategoryUI() {
        this.ctrl = new ParameterCategoryController();
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
            boolean exception = false;
            do {
                try {
                    String code = Utils.readLineFromConsole("Please enter the code of the new Parameter Category");
                    String name = Utils.readLineFromConsole("Please enter the name of the new Parameter Category");
                    ctrl.createParameterCategory(code, name);
                    exception = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("An error occurred during the creation during the creation of the Parameter please try again");
                    exception = true;
                }
            }while (exception);

            cont = Utils.confirm("The following Category was created do you want to save?" + ctrl.getpc());
            if (cont) {

                if (ctrl.saveParameterCategory()){
                    System.out.println("The Category was saved with success");
                }

            } else {
                System.out.println("Couldn't save the Parameter please try again ");
            }


        } while (!cont);

    }
}
