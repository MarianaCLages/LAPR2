package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.util.List;

public class ViewResultsController {

    private Company company;
    private TestStore store;
    private ClientStore cstore;

    public ViewResultsController(Company company) {

        this.company = company;
        cstore = App.getInstance().getCompany().getClientList();
        store = App.getInstance().getCompany().getTestList();
    }

    public ViewResultsController() {
        this(App.getInstance().getCompany());
    }

    public void sortedDateList() {
        Client client = cstore.getClientByEmail(App.getInstance().getCurrentUserSession().getUserId().toString());
        store.sortDate(client.getTinNumber());
    }

    public String getDiagnosis(String test) {
        System.out.println(test);
        String[] testSplitted = test.split(",");
        String[] idTestSplitted = testSplitted[1].split("=");
        Test t = store.getTestByCode(idTestSplitted[1]);
        System.out.println(idTestSplitted[1]);
        return t.getDiagnosis();
    }

    public List<String> getTestSortedList(){

        return store.getTestSortedListString();
    }
}
