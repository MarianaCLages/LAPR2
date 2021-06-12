package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.ImportTests;
import app.domain.model.Test;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.io.IOException;

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


}
