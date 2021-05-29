package app.controller;


import app.domain.mappers.TestListMapper;
import app.domain.mappers.dto.TestDTO;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.TestStore;

import java.util.List;

/**
 * Controller of the UserStory : Create a Report
 */

public class CreateReportController {

    private Company company;
    private TestStore tList;
    private Test t;

    /**
     * Constructor of the class
     * @param company instance of Company
     */

    public CreateReportController(Company company) {
        this.company = company;
    }

    public CreateReportController() {
        this(App.getInstance().getCompany());
    }

    /**
     * This method aims to 
     * @return the test list
     */

    public List<TestDTO> tList() {
        this.tList = company.getTestList().getListOfTestsAnalysed();
        TestListMapper typeMapper = new TestListMapper();
        return typeMapper.toDTO(tList);
    }

    public String getResults(String testId) {
        this.t = tList.getTestByCode(testId);
        return t.getResults();
    }

    public void createReport(String diagnosis) {
        t.createReport(diagnosis);
    }

    public void saveReport() {
        t.saveReport();
    }


}
