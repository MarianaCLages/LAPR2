package app.controller;
import app.domain.model.*;

import java.util.List;

public class ParameterController {

    private Company company;
    private Parameter pc;
    private ParameterStore store;
    private ParameterCategoryStore catStore;

    public ParameterController() {

        this(App.getInstance().getCompany());
    }

    public ParameterController(Company company) {
        this.company = company;
        this.pc = null;

    }


    public void createParameter(String code, String name, String description, ParameterCategory cat) {
        store = company.getParameterList();
        store.CreateParameter(code,name,description,cat);
    }
    public ParameterCategoryStore getParameterCategoryList() {
        return this.catStore = company.parameterCategoryList();
    }


    public Parameter getpc(){
        return store.getPc();
    }

    public boolean saveParameter() {
        return this.store.saveParameter();
    }
}
