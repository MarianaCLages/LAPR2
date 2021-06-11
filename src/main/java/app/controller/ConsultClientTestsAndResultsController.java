package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.util.ArrayList;
import java.util.List;

public class ConsultClientTestsAndResultsController {

    ClientStore clientsStore;
    TestStore testStore;
    List<Client> clientListOrder;
    Client client;

    public ConsultClientTestsAndResultsController(){
        App app = App.getInstance();
        Company company = app.getCompany();
        clientsStore = company.getClientList();
        testStore = company.getTestList();
        clientListOrder = new ArrayList<>();
    }

    public List<Client> getClientListTin(){
        clientListOrder = new ArrayList<>();
        clientListOrder.addAll(clientsStore.orderClientListByTin());
        return clientListOrder;
    }

    public List<Client> getClientListName(){
        clientListOrder = new ArrayList<>();
        clientListOrder.addAll(clientsStore.orderClientListByName());
        return clientListOrder;
    }

    public Client selectedClient(String tin){
        for (Client client1 : clientListOrder){
            if (tin.equalsIgnoreCase(client1.getTinNumber())){
                client = client1;
            }
        }
        return client;
    }

    public List<Test> getValidatedTestList(){
        return testStore.getValidatedTestList(client);
    }

    public Test selectedTest(String tin, List<Test> testList){
        Test test = null;

        for (Test test1 : testList){
            if (tin.equalsIgnoreCase(test1.getClientTin())){
                test = test1;
            }
        }
        return test;
    }

    public String toString(Test test) {
        return String.format("TestCode = %s, Client TIN = %s, NHS number = %s, Test type = %s, Date of validation = %tD, Test result = %s", test.getTestCode(), test.getClientTin(), test.getTestNhsNumber(), test.getTestType().getDescription(), test.getValidatedDate(), test.getResults());
    }
}