package app.controller;
import app.domain.model.Company;
import app.domain.model.*;

public class ClinicalAnalysisLabController {
    private Company company;
    private ClinicalAnalysisLabStore store;
    private ClinicalAnalysisLab cal;

    public ClinicalAnalysisLabController() {this(App.getInstance().getCompany());}

    public ClinicalAnalysisLabController(Company company) {
        this.company = company;
        this.cal = null;
    }

    public boolean createClinicalAnalysisLab(String name, String address, String id, String tin, String phonenumber) {
        store = company.getClinicalAnalysisLabList();
        if (store.CreateClinicalAnalysisLab( name,  address,  id,  tin,  phonenumber)){
            return true;
        }else {
            return false;
        }

    }

    public boolean saveTestType(){return this.store.saveClinicalAnalysisLab(cal);}

}