package app.controller;


import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.stores.ParameterCategoryStore;
import app.domain.stores.TestTypeStore;

/**
 * Controller of the UserStory : Specify a new type of test
 */
public class TestTypeController {

    private Company company;
    private TestTypeStore store;
    private ParameterCategoryStore cat;

    /**
     * Constructor of the class, gets an instance of the company class
     */
    public TestTypeController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor of the class, receives an instance of the company class
     *
     * @param company instance of Company
     */
    public TestTypeController(Company company) {
        this.company = company;
        store = company.getTestTypeList();
    }

    /**
     * Creates a new TestType instance, firstly creates a instance of TestTypeStore and then call the method of this instance that creates the TestType instance
     *
     * @param testID           ID of Type if test
     * @param description      simple description of the type of test
     * @param collectingMethod collecting methods of the type of test
     * @param cat              list of Parameter Categories associated with the test
     */
    public void createTestType(String testID, String description, String collectingMethod, ParameterCategoryStore cat) {

        store.CreateTestType(testID, description, collectingMethod, cat);
    }

    /**
     * Calls the method of the instance of TestTypeStore that saves the TestType instance in the ArrayList
     *
     * @return success of the operation
     */
    public boolean saveTestType() {
        return this.store.saveTestType();
    }

    /**
     * @return the list with all the Parameter Categories in the system
     */
    public ParameterCategoryStore getParameterCategoryList() {
        return company.getParameterCategoryList();
    }

    /**
     * @return an Type of Test instance
     */
    public TestType getTestT() {
        return store.getTestT();
    }


}
