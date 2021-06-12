package app.ui.console;

import app.domain.model.Client;
import app.domain.stores.ClientStore;

public class ClientUpdateUI implements Runnable {

    private ClientStore clientStore;

    public ClientUpdateUI(ClientStore clientStore){
        this.clientStore = clientStore;
    }

    @Override
    public void run() {
        Client client = clientStore.getClient();

        System.out.println("Clients current data: " + client.toString());
    }
}