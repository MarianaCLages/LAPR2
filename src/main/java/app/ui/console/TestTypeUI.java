package app.ui.console;

import app.controller.TestTypeController;
import app.domain.model.ParameterCategory;
import app.domain.model.ParameterCategoryStore;
import app.ui.console.utils.Utils;

public class TestTypeUI implements Runnable {
    private TestTypeController ctrl;
    private ParameterCategoryStore catStore;

    public TestTypeUI() {
        this.ctrl = new TestTypeController();
        this.catStore = new ParameterCategoryStore();
    }


    @Override
    public void run() {
        boolean cont = true;
        boolean leave = false;
        if (this.ctrl.getParameterCategoryList() == null || this.ctrl.getParameterCategoryList().isEmpty()) {
            System.out.println("There are no Categories added to the system please add at least one before trying to create a new type of test.");
        } else {
            do {
                boolean exception = false;
                do{
                    ParameterCategory cat = (ParameterCategory) Utils.showAndSelectOne(this.ctrl.getParameterCategoryList().getList(), "Select a Category");
                    this.catStore.add(cat);
                    leave = Utils.confirm("Do you want to select more categories? (s/n)");
                }while (!leave);
                do {
                    try {
                        String testID = Utils.readLineFromConsole("Please enter the ID code of the new type of test");
                        String description = Utils.readLineFromConsole("Please enter the description of the new type of test");
                        String collectingMethod = Utils.readLineFromConsole("Please enter the collecting method of the new type of test");

                        this.ctrl.createTestType(testID, description, collectingMethod, this.catStore);
                        this.ctrl.saveTestType();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("An error occurred during the creation during the creation of the type of test please try again");
                        exception = true;
                    }
                } while (exception);
                cont = Utils.confirm("The following type of test was created do you want to save? (s/n) " + this.ctrl.getTestT().toString());
                if (cont) {

                    if (this.ctrl.saveTestType()) {
                        System.out.println("The type of test was saved with success");
                    }

                } else {
                    System.out.println("Couldn't save the type of test please try again ");
                }

            } while (!cont);

        }
    }
}

