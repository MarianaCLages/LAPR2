package app.controller;
import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.ParameterCategoryStore;

public class ParameterCategoryController {

    private Company company;
    private ParameterCategory pc;
    private ParameterCategoryStore store;

    public ParameterCategoryController() {
        this(App.getInstance().getCompany());
    }

    public ParameterCategoryController(Company company) {
        this.company = company;
        this.pc = null;

    }


    public boolean createParameterCategory(String code, String name) {
        store = company.getParameterCategoryList();
        if (store.CreateParameterCategory(code, name)){
            return true;
        }else {
            return false;
        }
    }

    public boolean saveParameterCategory() {
        return this.store.saveParameterCategory(pc);
    }
}
