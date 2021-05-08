package app.ui.console;

import app.controller.TestTypeController;
import app.domain.model.ParameterCategory;
import app.domain.model.ParameterCategoryStore;
import app.domain.model.TestType;
import app.domain.model.TestTypeStore;
import app.ui.console.utils.Utils;

import java.util.List;

public class TestTypeUI implements Runnable{
    private TestTypeController ctrl;
    private ParameterCategoryStore catStore;
    private List<String> snList;
    private TestTypeStore tType;

    public TestTypeUI() {
        this.ctrl = new TestTypeController();
    }


    @Override
    public void run() {
        boolean cont = true;
        snList.add("Yes");
        snList.add("No");
        int sair = 0;
        if (ctrl.getParameterCategoryList() == null || ctrl.getParameterCategoryList().isEmpty()) {
            System.out.println("There are no Categories added to the system please add at least one before trying to create a new type of test.");
        } else {
            do {
                boolean exception = false;
                while (sair==0) {
                    ParameterCategory cat = (ParameterCategory) Utils.showAndSelectOne(ctrl.getParameterCategoryList().getList(), "Select a Category");
                    catStore.add(cat);
                    String answer = (String) Utils.showAndSelectOne(snList, "Do you want to select more categories?");
                    if (answer.equals("No")) {sair=1;}
                }
                do {
                    try {
                        String testID = Utils.readLineFromConsole("Please enter the ID code of the new type of test");
                        String description = Utils.readLineFromConsole("Please enter the description of the new type of test");
                        String collectingMethod = Utils.readLineFromConsole("Please enter the collecting method of the new type of test");

                        TestType t = new TestType(testID, description, collectingMethod, catStore);
                        tType.add(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("An error occurred during the creation during the creation of the type of test please try again");
                        exception = true;
                    }
                } while (exception);
                cont = Utils.confirm("The following type of test was created do you want to save?" + ctrl.getTestT().toString());
                if (cont) {

                    if (ctrl.saveTestType()) {
                        System.out.println("The type of test was saved with success");
                    }

                } else {
                    System.out.println("Couldn't save the type of test please try again ");
                }

            } while (!cont);

        }
    }
}

