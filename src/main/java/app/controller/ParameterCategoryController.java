package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.ParameterCategoryStore;
import auth.UserSession;

public class ParameterCategoryController {

    private Company company;
    private ParameterCategoryStore store;

    public ParameterCategoryController() {
        this(App.getInstance().getCompany());

    }

    public ParameterCategoryController(Company company) {
        this.company = company;

    }


    public void createParameterCategory(String code, String name) {
        store = company.getParameterCategoryList();
        store.CreateParameterCategory(code,name);
    }

    public String getpc(){
        return store.getPc().toString();
    }

    public boolean saveParameterCategory() {
        return this.store.saveParameterCategory();
    }
}
