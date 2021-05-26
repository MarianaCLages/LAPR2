package app.ui.console;

import app.controller.CreateTestController;
import app.domain.mappers.dto.CategoryListDTO;
import app.domain.mappers.dto.ParameterDTO;
import app.domain.mappers.dto.TestTypeDTO;
import app.ui.console.utils.Utils;

import java.util.List;

public class RegisterTestUI implements Runnable {
    private CreateTestController ctrl;
    private TestTypeDTO testType;
    private List<CategoryListDTO> categories;
    private CategoryListDTO category;
    private List<ParameterDTO> parameters;
    private ParameterDTO parameter;

    public RegisterTestUI() {
        this.ctrl = new CreateTestController();
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
        ctrl.getLists();
        boolean cont = true;
        boolean cat = true;
        boolean pa = true;

        do {
            String tin = Utils.readLineFromConsole("Please enter the Tin of the Client ()");
            ctrl.getLists();
            if (ctrl.existClient(tin)) {
                boolean exception = false;
                do {
                    String testNhsCode = Utils.readLineFromConsole("Please enter the Test NHS Code (12 characters)");
                    try {
                        this.testType = (TestTypeDTO) Utils.showAndSelectOne(ctrl.getTestTypeList(), "Please select one Type of Test");

                        this.categories = ctrl.getCategories(testType.getTestID());
                        do {
                            this.category = (CategoryListDTO) Utils.showAndSelectOne(categories, "Please select one Category\n");
                            do {
                                this.parameters = ctrl.getParameters(this.category.getCode());
                                this.parameter = (ParameterDTO) Utils.showAndSelectOne(parameters, "Please select one Parameter");

                                ctrl.addParameter(parameter.getCode());
                                pa = Utils.confirm("Do you want to select other parameter");
                            } while (pa);
                            cat = Utils.confirm("Do you want to select other category");
                        } while (cat);
                    } catch (Exception e) {
                        System.out.println("There must exist Types of Tests, Parameter Categories and Parameters in the system \n");
                    }
                    try {
                        ctrl.createTest(testNhsCode, tin);
                        exception = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("An error occurred during the creation during the creation of the Test please try again\n");
                        exception = true;

                    }
                } while (exception);

                cont = Utils.confirm("The following Test was created do you want to save? (s/n) \n" + ctrl.getTest());
                if (cont) {
                    ctrl.saveTest();
                }
            } else {
                System.out.println("The client does not exists please register the client before registering the test");
                cont = true;
            }
        } while (!cont);


    }
}
