package app.controller;


import app.domain.model.Company;
import app.domain.stores.ParameterCategoryStore;

/**
 * Controller of the UserStory : Create a new Parameter Category
 */
public class ParameterCategoryController {

    private final Company company;
    private final ParameterCategoryStore store;

    /**
     * Constructor of the class, gets an instance of the company class
     */
    public ParameterCategoryController() {
        this(App.getInstance().getCompany());

    }

    /**
     * Constructor of the class, receives an instance of the company class
     *
     * @param company instance of Company
     */
    public ParameterCategoryController(Company company) {
        this.company = company;
        store = company.getParameterCategoryList();
    }

    /**
     * Creates a new Parameter Category instance, firstly creates a instance of ParameterCategoryStore and then call the method of this instance that creates the Parameter Category instance
     *
     * @param code unique code needed to identify the Parameter Category
     * @param name short name that characterize the Parameter Category
     */
    public void createParameterCategory(String code, String name) {
        store.createParameterCategory(code, name);
    }

    /**
     * @return String that represents the Parameter Category instance
     */
    public String getpc() {
        return store.getPc().toString();
    }

    /**
     * Calls the method of the instance of ParameterCategoryStore that saves the Parameter Category instance in the ArrayList
     *
     * @return success of the operation
     */
    public boolean saveParameterCategory() {
        this.store.saveParameterCategory();
        return company.saveCompany();
    }
}
