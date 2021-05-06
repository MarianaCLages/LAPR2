package app.controller;
import app.domain.model.Company;
import app.domain.model.*;

public class ClinicalAnalysisLabController {
    private Company company;
    private ClinicalAnalysisLabStore store;
    private ClinicalAnalysisLab cal;
    private TestTypeStore tType;

    public ClinicalAnalysisLabController() {
        this(App.getInstance().getCompany());
    }

    public ClinicalAnalysisLabController(Company company) {
        this.company = company;
        this.cal = null;
    }

    /*
    public void createClinicalAnalysisLab(String name, String address, String id, String tin, String phoneNumber, TestTypeStore tType) {
        store = cal.getClinicalAnalysisLabStore();
        store.CreateClinicalAnalysisLab(name, address, id, tin, phoneNumber, tType );

    } */

    public boolean saveTestType(){return this.store.saveClinicalAnalysisLab(cal);}

}


