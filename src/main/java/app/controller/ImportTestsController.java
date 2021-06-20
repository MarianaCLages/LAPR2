package app.controller;

import app.domain.model.Company;
import app.domain.model.ImportTests;


import java.util.List;

/**
 * Controller of the UserStory : Imports a new Test
 */
public class ImportTestsController {

    private Company company;
    private ImportTests importt = new ImportTests();


    /**
     * Constructor of the class
     * @param company instance of Company
     */
    public ImportTestsController(Company company) {
        this.company = company;
    }

    /**
     * This method aims to instance the Company
     */
    public ImportTestsController() {
        this(App.getInstance().getCompany());
    }

    /**
     * This method reads the tests from the CSV File
     * @param filepath path of the CSV file
     */
    public void readTestsfromFile(String filepath) {
        importt.readTestFromCSV(filepath);
    }

    /**
     * This method  saves the test list from the file
     * @return
     */
    public List<String> saveFileTestList() {
        return importt.getTestFileList();
    }


}