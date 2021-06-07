package app.controller;


import app.domain.model.ClinicalAnalysisLab;
import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.stores.ClinicalAnalysisLabStore;
import app.domain.stores.TestTypeStore;

/**
 * Controller of the UserStory : Register a new clinical analysis laboratory
 */
public class ClinicalAnalysisLabController {
    private Company company;
    private ClinicalAnalysisLabStore store;
    private ClinicalAnalysisLab cal;
    private TestTypeStore typeTStoreCli;
    private TestTypeStore typeTStore;


    /**
     * Constructor of the class, gets an instance of the company class
     */
    public ClinicalAnalysisLabController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor of the class, receives an instance of the company class
     *
     * @param company instance of Company
     */
    public ClinicalAnalysisLabController(Company company) {
        this.typeTStoreCli = new TestTypeStore();
        this.company = company;
        this.cal = null;
    }

    /**
     * Creates a new ClinicalAnalysisLab  instance, firstly creates a instance of ClinicalAnalysisLabStore and then call the method of this instance that creates the ClinicalAnalysisLab instance
     *
     * @param name        name of the Clinical Analysis Lab
     * @param address     address of the Clinical Analysis Lab
     * @param id          id of Clinical Analysis Lab
     * @param tin         TIN of Clinical Analysis Lab
     * @param phoneNumber Phone Number Clinical Analysis Lab
     */
    public void createClinicalAnalysisLab(String name, String address, String id, String tin, String phoneNumber) {
        store = company.getClinicalAnalysisLabList();
        store.CreateClinicalAnalysisLab(name, address, id, tin, phoneNumber, this.typeTStoreCli);

    }

    /**
     * @return the list with all the Type if Tests in the system
     */
    public TestTypeStore getTypetestList() {
        return this.typeTStore = company.getTestTypeList();
    }

    /**
     * @return String that represents the ClinicalAnalysisLab instance
     */
    public ClinicalAnalysisLab getcal() {
        return store.getCal();
    }

    /**
     * This method adds an type of test to a TestStore
     *
     * @param t Type of test to be added
     * @return boolean value that represents the success of the operation
     */
    public boolean addToList(TestType t) {
        return typeTStoreCli.add(t);

    }

    /**
     * Calls the method of the instance of ClinicalAnalysisLabStore that saves the ClinicalAnalysisLab instance in the ArrayList
     *
     * @return success of the operation
     */
    public boolean saveClinicalAnalysisLab() {
        this.store.saveClinicalAnalysisLab();
        return company.saveCompany();
    }

}
