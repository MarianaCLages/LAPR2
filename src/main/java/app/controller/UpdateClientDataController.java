package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.stores.ClientStore;

import java.util.Date;

    public class UpdateClientDataController {

        private Company company = App.getInstance().getCompany();
        private final Client client;
        private final ClientStore clientStore = company.getClientList();

        public UpdateClientDataController(){
            String email = App.getInstance().getCurrentUserSession().getUserId().toString();
            this.client = company.getClientList().getClientByEmail(email);
        }

        public void updateClientData(String cc, String nhs, Date birthDate, char sex, String tinNumber, String phoneNumber, String email, String name){
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

