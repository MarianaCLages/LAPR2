package app.controller;
import app.domain.model.Company;
import app.domain.model.*;

import java.util.List;

public class TestTypeController {

    private Company company;
    private TestTypeStore store;
    private TestType testT;
    private ParameterCategoryStore cat;

    public TestTypeController() {this(App.getInstance().getCompany());}

    public TestTypeController(Company company) {
        this.company = company;
        this.testT = null;
    }


    public void createTestType(String testID, String description, String collectingMethod, ParameterCategoryStore catList){
        store = company.getTestTypeList();
        store.CreateTestType(testID,description,collectingMethod,catList);
    }

    public boolean saveTestType() {
        return this.store.saveTestType();
    }


}
