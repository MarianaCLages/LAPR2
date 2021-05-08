package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategoryStore;

public class ParameterCategoryController {

    private Company company;
    private ParameterCategoryStore store;

    /**
     *
     */
    public ParameterCategoryController() {
        this(App.getInstance().getCompany());

    }

    /**
     *
     * @param company
     */
    public ParameterCategoryController(Company company) {
        this.company = company;

    }

    /**
     *
     * @param code
     * @param name
     */
    public void createParameterCategory(String code, String name) {
        store = company.getParameterCategoryList();
        store.CreateParameterCategory(code,name);
    }

    /**
     *
     * @return
     */
    public String getpc(){
        return store.getPc().toString();
    }

    /**
     *
     * @return
     */
    public boolean saveParameterCategory() {
        return this.store.saveParameterCategory();
    }
}
