package app.ui.console;

import app.controller.UpdateClientDataController;
import app.domain.model.Client;
import app.domain.stores.ClientStore;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientUpdateUI implements Runnable {

    private UpdateClientDataController clientDataController;
    private List<String> sexes;

    public ClientUpdateUI(){
       clientDataController = new UpdateClientDataController();
       sexes = new ArrayList<>();
    }

    @Override
    public void run() {
        sexes.add("Male");
        sexes.add("Female");
        sexes.add("Other");

       


    }
}