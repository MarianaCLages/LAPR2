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
     * Constructor of the class, receives an instance of the company class
     * @param company instance of Company
     */
    public CreateReportController(Company company) {
        this.company = company;
    }

    /**
     * Constructor of the class, gets an instance of the company class
     */
    public CreateReportController() {
        this(App.getInstance().getCompany());
    }

    /**
     * This method aims to create a list filled with objects with characteristics from the objects meant to be presented
     * @return the test list
     */
    public List<TestDTO> tList() {
        this.tList = company.getTestList().getListOfTestsAnalysed();
        TestListMapper typeMapper = new TestListMapper();
        return typeMapper.toDTO(tList);
    }

    /**
     * This method searches through the Test list the test associated with a specified code and returns the results found
     *
     * @param testId id of the test
     * @return a string with all the results associated with the test
     */
    public String getResults(String testId) {
        this.t = tList.getTestByCode(testId);
        return t.getResults();
    }

    /**
     * Creates a new Report object by calling its constructor
     *
     * @param diagnosis text contained in the report
     */
    public void createReport(String diagnosis) {
        t.createReport(diagnosis);
    }

    /**
     * This method calls the method of the instance of Test that saves the Report
     */
    public void saveReport() {
        t.saveReport();
        company.saveCompany();
    }


}
