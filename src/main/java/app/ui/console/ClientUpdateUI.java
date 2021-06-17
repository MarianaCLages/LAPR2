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

        boolean isDataCorrect = false;

        do {
            try {
                String cc = Utils.readLineFromConsole("Please enter the new cc");
                String name = Utils.readLineFromConsole("Please enter the new name");
                String nhs = Utils.readLineFromConsole("Please enter the new nhs");
                Date birthDate = Utils.readDateFromConsole("Please enter the new birth date");
                int sexIndex = Utils.showAndSelectIndex(sexes, "Please choose your updated sex");
                char sex = ' ';
                if (sexIndex == 0) {
                    sex = 'M';
                } else if (sexIndex == 1) {
                    sex = 'S';
                }
                String tif = Utils.readLineFromConsole("Please enter the new TIF number");
                String phoneNumber = Utils.readLineFromConsole("Please enter the new phone number");
                String email = Utils.readLineFromConsole("Please enter the email of the new Client");
                clientDataController.updateClientData(cc, nhs, birthDate, sex, tif, phoneNumber, email, name);
                isDataCorrect = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("An error occurred during the creation during the creation of the Parameter please try again");
                isDataCorrect = false;
            }
        } while (!isDataCorrect);


    }
}