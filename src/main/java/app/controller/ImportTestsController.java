package app.controller;

import app.domain.model.Company;
import app.domain.model.ImportTests;

import java.io.IOException;
import java.util.List;

public class ImportTestsController {

    private Company company;
    private String fileName;
    private ImportTests importt = new ImportTests();

    public ImportTestsController(Company company) throws IOException {
        this.company = company;
    }

    public ImportTestsController() throws IOException {
        this(App.getInstance().getCompany());
    }


    public void readTestsfromFile(String fileName) {
        importt.readTestFromCSV(fileName);
    }

    public List<String> saveFileTestList(){
        return importt.getTestFileList();

    }


}
