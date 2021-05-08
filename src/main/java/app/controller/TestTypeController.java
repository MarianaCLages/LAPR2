package app.controller;

import app.domain.model.Company;
import app.domain.model.*;

public class TestTypeController {

    private Company company;
    private TestTypeStore store;
    private ParameterCategoryStore cat;

    public TestTypeController() {this(App.getInstance().getCompany());}

    public TestTypeController(Company company) {
        this.company = company;
    }


    public void createTestType(String testID, String description, String collectingMethod, ParameterCategoryStore cat){
        store = company.getTestTypeList();
        store.CreateTestType(testID,description,collectingMethod,cat);
    }

    public boolean saveTestType() {
        return this.store.saveTestType();
    }


    public ParameterCategoryStore getParameterCategoryList() {
        return this.cat = company.parameterCategoryList();
    }

    public TestType getTestT() {
        return store.getTestT();
    }


}
