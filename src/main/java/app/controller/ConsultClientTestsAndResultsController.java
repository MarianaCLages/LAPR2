package app.controller;

import app.domain.model.*;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.util.ArrayList;
import java.util.List;

public class ConsultClientTestsAndResultsController {

    ClientStore clientsStore;
    TestStore testStore;
    List<Client> clientListOrder;
    Client client;
    Test test;

    /**
     * Constructor.
     */
    public ConsultClientTestsAndResultsController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        clientsStore = company.getClientList();
        testStore = company.getTestList();
        clientListOrder = new ArrayList<>();
    }

    /**
     * Gets the client list ordered by the clients' TIN numbers.
     *
     * @return the client list ordered by the clients' TIN numbers
     */
    public List<Client> getClientListTin() {
        clientListOrder = new ArrayList<>();
        clientListOrder.addAll(clientsStore.sortClientListByTin());
        return clientListOrder;
    }

    /**
     * Gets the client list ordered by the clients' names.
     *
     * @return the client list ordered by the clients' names
     */
    public List<Client> getClientListName() {
        clientListOrder = new ArrayList<>();
        clientListOrder.addAll(clientsStore.sortClientListByName());
        return clientListOrder;
    }

    /**
     * Gets the selected client.
     *
     * @param tin the TIN of the selected client
     * @return the selected client
     */
    public Client selectedClient(String tin) {
        for (Client client1 : clientListOrder) {
            if (tin.equalsIgnoreCase(client1.getTinNumber())) {
                client = client1;
            }
        }
        return client;
    }

    /**
     * Gets the selected client's list of validated tests.
     *
     * @return the list of validated tests of the selected client
     */
    public List<Test> getValidatedTestList() {
        return testStore.getValidatedTestList(client);
    }

    /**
     * Gets the selected client's test.
     *
     * @param nhs      the test's NHS code
     * @param testList the list of tests
     * @return the client's selected test
     */
    public Test selectedTest(String nhs, List<Test> testList) {
        for (Test test1 : testList) {
            if (nhs.equalsIgnoreCase(test1.getTestNhsNumber())) {
                test = test1;
            }
        }
        return test;
    }

    /**
     * Returns the textual description of the test results/details in the format: test code, client TIN, NHS number,
     * test type and date of validation.
     *
     * @param test the selected test
     * @return the test results/details
     */
    public String toString(Test test) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TestCode = ").append(test.getTestCode()).append("\nClient TIN = ").append(test.getClientTin()).append("\nNHS number = ").append(test.getTestNhsNumber()).append("\nTest type = ").append(test.getTestType().getDescription()).append("\nDate of validation = ").append(test.getValidatedDate().getYear()).append("-").append(test.getValidatedDate().getMonthValue()).append("-").append(test.getValidatedDate().getDayOfMonth()).append(" ").append(test.getValidatedDate().getHour()).append(":").append(test.getValidatedDate().getMinute()).append(":").append(test.getValidatedDate().getSecond());

        for (TestParameter tp : test.getTestParam()) {
            stringBuilder.append("\n").append(tp.getTestParameterResult().toString());
        }

        return stringBuilder.toString();
    }
}