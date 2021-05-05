package app.controller;

import app.domain.model.ClientStore;
import app.domain.model.Company;

public class ClientController {

    private Company company;
    private ClientStore store;

    public ClientController() {

        this(App.getInstance().getCompany());
    }

    public ClientController(Company company) {
        this.company = company;

    }

    public void getStore() {
        this.store = this.company.getClientList();
    }

    public boolean saveClient(Company company) {
        boolean saved = false;

        saved = store.saveClient();
        saved = store.addUser(company);

        return saved;
    }


}

