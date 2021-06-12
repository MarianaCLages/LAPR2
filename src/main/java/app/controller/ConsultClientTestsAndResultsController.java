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
            if (tin.equalsIgnoreCase(test1.getTestNhsNumber())){
                test = test1;
            }
        }
        return test;
    }

    public String toString(Test test) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TestCode = ").append(test.getTestCode()).append("\nClient TIN = ").append(test.getClientTin()).append("\nNHS number = ").append(test.getTestNhsNumber()).append("\nTest type = ").append(test.getTestType().getDescription()).append("\nDate of validation = ").append(test.getValidatedDate().getYear()).append("-").append(test.getValidatedDate().getMonthValue()).append("-").append(test.getValidatedDate().getDayOfMonth()).append(" ").append(test.getValidatedDate().getHour()).append(":").append(test.getValidatedDate().getMinute()).append(":").append(test.getValidatedDate().getSecond());

        System.out.println(stringBuilder);
        for (TestParameter tp : test.getTestParam()) {
            stringBuilder.append("\n").append(tp.getTestParameterResult().toString());
        }

        return stringBuilder.toString();
    }
}