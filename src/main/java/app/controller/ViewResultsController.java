package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.util.List;

public class ViewResultsController  {

    private Company company;
    private TestStore store;
    private Test test;
    private ClientStore cstore;

    public ViewResultsController(Company company) {

        this.company = company;
    }

    public ViewResultsController() {
        this(App.getInstance().getCompany());
    }

    public void sortedDateList(){
        Client client = cstore.getClientByEmail(company.getUserID());
        store.sortDate(client.getTinNumber());
    }

    public String getResult(String test) {
        String[] testSplitted = test.split(";");
        String[] idTestSplitted = testSplitted[0].split("=");
        Test t = store.getTestByCode(idTestSplitted[1]);
        return t.getResults();
    }

    public List<String> getTestSortedList(){
        return store.getTestSortedListString();
    }
}
