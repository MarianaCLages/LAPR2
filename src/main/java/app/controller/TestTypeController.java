package app.controller;
import app.domain.model.Company;
import app.domain.model.*;

public class TestTypeController {
    private Company company;
    private TestTypeStore store;
    private TypeTest testT;

    public TestTypeController() {this(App.getInstance().getCompany());}

    public TestTypeController(Company company) {
        this.company = company;
        this.testT = null;
    }

    public boolean createTestType(String testID, String collectingMethod, String description) {
        store = company.getTestTypeList();
        if (store.CreateTestType(testID, collectingMethod, description)){
            return true;
        }else {
            return false;
        }

    }


    public boolean saveTestType(){return this.store.saveTestType(testT);}

}
