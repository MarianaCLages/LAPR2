package app.controller;

import app.domain.model.Client;
import app.domain.model.ClientStore;
import app.domain.model.Company;

import java.util.Date;

public class ClientController {

    private Company company;
    private ClientStore store;

    public ClientController() {

        this(App.getInstance().getCompany());
    }

    public ClientController(Company company) {
        this.company = company;

    }
    public void createClient(String cc, String nhs, Date birhDate, char sex, String tifNumber, String phoneNumber,String email,String name) {
        store = company.getClientList();
        store.CreateClient(phoneNumber, cc, nhs,tifNumber, birhDate, sex,  email, name);
    }

    public String getClient(){
        return store.getClient().toString();
    }


    public boolean saveClient() {
        boolean saved = false;

        saved = store.saveClient();
        store.addUser(this.company);

        return saved;
    }


}

