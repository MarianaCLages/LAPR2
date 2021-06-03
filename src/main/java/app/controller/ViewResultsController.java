package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.util.List;

public class ViewResultsController  {

    private Company company;
    private Client client;
    private TestStore store;
    private Test test;
    private ClientStore cstore;

    public ViewResultsController() {
        this(App.getInstance().getCompany());
    }

    public ViewResultsController(Company company) {
        this.company = company;
    }



    public List<Test> sortedDateList(){
        this.client = cstore.getClientByEmail(company.getUserID());
        return store.sortDate(this.client.getTinNumber());
    }

    public String getResults() {
        return this.test.getResults();
    }

}
