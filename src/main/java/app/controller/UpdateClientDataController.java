package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.stores.ClientStore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateClientDataController {

    private Company company = App.getInstance().getCompany();
    private final Client client;
    private final ClientStore clientStore = company.getClientList();

    public UpdateClientDataController() {

        String email = App.getInstance().getCurrentUserSession().getUserId().toString();
        this.client = company.getClientList().getClientByEmail(email);
    }

    public String getClient(){
        return client.toString();
    }

    public void changeData(int i, String data) {
        switch (i) {
            case 1:
                this.client.setCc(data);
                break;


            case 2:
                this.client.setNhs(data);
                break;

            case 3:
                //DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                //this.client.setBirthDate(date);
                break;

            case 4:
                this.client.setSex(data);
                break;

            case 5:
                this.client.setTinNumber(data);
                break;

            case 6:
                this.client.setPhoneNumber(data);
                break;

            case 7:
                this.client.setEmail(data);
                break;

            case 8:
                this.client.setName(data);
                break;

            default:
                break;
        }

    }

    public void updateClientData(String cc, String nhs, Date birthDate, char sex, String tinNumber, String phoneNumber, String email, String name) {
        this.client.setCc(cc);
        this.client.setNhs(nhs);
        this.client.setBirthDate(birthDate);
        this.client.setSex(String.valueOf(sex));
        this.client.setTinNumber(tinNumber);
        this.client.setPhoneNumber(phoneNumber);
        this.client.setEmail(email);
        this.client.setName(name);

        clientStore.setClient(this.client);
        clientStore.saveClient();
        company.saveCompany();
    }
}

