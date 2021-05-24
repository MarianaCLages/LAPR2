package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.stores.ParameterCategoryStore;
import app.domain.stores.ParameterStore;

/**
 * Controller of the UserStory : Specify a new parameter
 */
public class ParameterController {

    private Company company;
    private ParameterStore store;
    private ParameterCategoryStore catStore;

    /**
     * Constructor of the class, gets an instance of the company class
     */
    public ParameterController() {

        this(App.getInstance().getCompany());
        store = company.getParameterList();

    }

    /**
     * Constructor of the class, receives an instance of the company class
     *
     * @param company instance of Company
     */
    public ParameterController(Company company) {
        this.company = company;
    }

    /**
     * Creates a new Parameter instance, firstly creates a instance of ParameterStore and then call the method of this instance that creates the Parameter instance
     * @param code unique code needed to identify the Parameter
     * @param description description that characterize the Parameter
     * @param name short name that characterize the Parameter
     * @param cat  category associated with the Parameter
     */
    public void createParameter(String code, String name, String description, ParameterCategory cat) {
        store.createParameter(code, name, description, cat);
    }

    /**
     * @return the list with all the Type if Tests in the system
     */
    public ParameterCategoryStore getParameterCategoryList() {
        return this.catStore = company.parameterCategoryList();
    }

    /**
     * @return String that represents the Parameter instance
     */
    public String getpc() {
        return store.getPc().toString();
    }

    /**
     * Calls the method of the instance of ParameterStore that saves the Parameter instance in the ArrayList
     *
     * @return success of the operation
     */
    public boolean saveParameter() {
        return this.store.saveParameter();
    }
}
