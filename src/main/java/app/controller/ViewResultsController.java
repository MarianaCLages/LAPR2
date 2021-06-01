package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.TestStore;

public class ViewResultsController {

    private Company company;
    private Client client;
    private TestStore store;
    private Test test;

    public ViewResultsController() {
        this(App.getInstance().getCompany());
    }

    public ViewResultsController(Company company) {
        this.company = company;
    }

    public String  CompareEmail(){
        company.getClientEmail(this.client).equals(company.getUserID());
            return company.getClientTin();
    }

    public String viewresults(){
        return store.getTestByTin(CompareEmail()).getResults();
    }


}
