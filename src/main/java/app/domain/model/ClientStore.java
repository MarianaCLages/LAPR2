package app.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientStore {
    private final List<Client> array;
    private Client client;

    public ClientStore() {
        this.array = new ArrayList<>();
    }

    public void CreateClient(String phoneNumber, String cc, String nhs, String tinNumber, Date birthDate, char sex, String email, String name) {
        this.client = new Client(phoneNumber, cc, nhs, tinNumber, birthDate, sex, email, name);
    }

    public boolean ValidateClient(Client client) {
        return client != null && !contains(client) && !exists(client);
    }

    private boolean exists(Client client) {
        for (Client c : array) {
            return c.getEmail().equals(client.getEmail()) || c.getPhoneNumber().equals(client.getPhoneNumber());
        }
        return false;
    }

    public boolean saveClient() {
        if (ValidateClient(this.client)) {
            array.add(this.client);
            return true;
        } else {
            return false;
        }
    }



    public boolean contains(Client client) {
        return this.array.contains(client);
    }

    public boolean addUser(Company company) {
        return client.addUser(company);
    }

    public Client getClient() {
        return client;
    }
}
