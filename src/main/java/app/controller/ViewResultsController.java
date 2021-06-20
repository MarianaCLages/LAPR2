package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.util.List;

/**
 * Controller of the UserStory : View the Results
 */
public class ViewResultsController {

    private Company company;
    private TestStore store;
    private ClientStore cstore;

    /**
     * This method aims to instance the Company, the Client and the Test Lists
     * @param company instance of Company
     */
    public ViewResultsController(Company company) {

        this.company = company;
        cstore = App.getInstance().getCompany().getClientList();
        store = App.getInstance().getCompany().getTestList();
    }

    /**
     * This method instances the Company
     */
    public ViewResultsController() {
        this(App.getInstance().getCompany());
    }

    /**
     * This method sorts the list by date
     */
    public void sortedDateList() {
        Client client = cstore.getClientByEmail(App.getInstance().getCurrentUserSession().getUserId().toString());
        store.sortDate(client.getTinNumber());
    }

    /**
     * This method gets the diagnosis
     * @param test string of test selected on UI
     * @return string with the diagnosis
     */
    public String getDiagnosis(String test) {
        System.out.println(test);
        String[] testSplitted = test.split(",");
        String[] idTestSplitted = testSplitted[1].split("=");
        Test t = store.getTestByCode(idTestSplitted[1]);
        System.out.println(idTestSplitted[1]);
        return t.getDiagnosis();
    }

    /**
     * This method gets the test list (string) sorted
     * @return list (string) with the test list sorted
     */
    public List<String> getTestSortedList(){

        return store.getTestSortedListString();
    }
}
