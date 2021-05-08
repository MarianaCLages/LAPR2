package app.controller;
import app.domain.model.Company;
import app.domain.model.*;

public class ClinicalAnalysisLabController {
    private Company company;
    private ClinicalAnalysisLabStore store;
    private ClinicalAnalysisLab cal;
    private TestTypeStore typeTStore;

    public ClinicalAnalysisLabController() {this(App.getInstance().getCompany());}

    public ClinicalAnalysisLabController(Company company) {
        this.company = company;
        this.cal = null;
    }

    public void createClinicalAnalysisLab(String name, String address, String id, String tin, String phoneNumber, TestType tType) {
        store = company.getClinicalAnalysisLabList();
        store.CreateClinicalAnalysisLab(name,address,id,tin,phoneNumber,tType);

    }

    public  TestTypeStore getTypetestList(){
        return this.typeTStore = company.TestTypeList();
    }

    public ClinicalAnalysisLab getcal(){
        return store.getCal();
    }

    public boolean saveClinicalAnalysisLab() {
        return this.store.saveClinicalAnalysisLab();
    }

}
