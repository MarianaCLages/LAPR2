package app.controller;

import app.domain.model.Company;
import app.domain.model.ImportTests;

import java.util.List;

public class ImportTestsController {

    private Company company;
    private ImportTests importt = new ImportTests();

    public ImportTestsController(Company company) {
        this.company = company;
    }

    public ImportTestsController() {
        this(App.getInstance().getCompany());
    }


    public void readTestsfromFile(String filepath) {
        importt.readTestFromCSV(filepath);
    }

    public List<String> saveFileTestList(){
        return importt.getTestFileList();

    }


}
