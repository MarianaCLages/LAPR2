package app.domain.stores;

import app.domain.model.Client;
import app.domain.model.Company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that represents an List of all the clients in the system
 */
public class ClientStore implements Serializable {
    private final List<Client> array;
    private Client client;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of Client
     */
    public ClientStore() {
        this.array = new ArrayList<>();
    }

    /**
     * This method creates a new Client object by calling one of his constructor depending on if the sex is specified or not
     *
     * @param phoneNumber unique phone number that belongs to client
     * @param cc          citizen card number of the client
     * @param nhs         national health system number of the client
     * @param tinNumber   tax identification number of the client
     * @param birthDate   birth date of the client
     * @param sex         sex of the client
     * @param email       unique email that belongs to client
     * @param name        name of the client
     */
    public void createClient(String phoneNumber, String cc, String nhs, String tinNumber, Date birthDate, char sex, String email, String name) {
        if (sex == 'M' || sex == 'F') {
            this.client = new Client(phoneNumber, cc, nhs, tinNumber, birthDate, sex, email, name);
        } else {
            this.client = new Client(phoneNumber, cc, nhs, tinNumber, birthDate, email, name);
        }
    }

    /**
     * This Method sets the client object in this class as a given client
     *
     * @param client client to be set as the client object in this class
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Checks if a instance of object Client is valid. An instance of a client is valid if it is not null, if don't already exists or if the email and phone number of the instance are not unique
     *
     * @param client instance of object Client
     * @return a boolean value that represents if the instance of object Client is valid
     */
    public boolean validateClient(Client client) {
        return client != null && !contains(client) && !exists(client);
    }

    /**
     * Checks if the Client client already exists in the system, it checks if the email or the phone number are unique
     *
     * @param client instance of object Client
     * @return a boolean value that represents if the Client already exists in the system
     */
    private boolean exists(Client client) {
        for (Client c : array) {
            return c.getEmail().equals(client.getEmail()) || c.getPhoneNumber().equals(client.getPhoneNumber());
        }
        return false;
    }

    public boolean exists(String tin) {
        for (Client c : array) {
            return c.getTinNumber().equals(tin);
        }
        return false;
    }

    /**
     * Saves the instance of object Client in the ArrayList, before adding the client it is verified if the instance of client is valid
     *
     * @return boolean value that represents the success of the operation
     */
    public boolean saveClient() {
        if (validateClient(this.client)) {
            array.add(this.client);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the instance of the Class client already exists in the ArrayList
     *
     * @param client Client instance that is going to be checked
     * @return boolean value that represents the success of the operation
     */
    public boolean contains(Client client) {
        return this.array.contains(client);
    }

    /**
     * Adds user to the system by calling the Client instance method
     *
     * @return boolean value that represents the success of the operation
     */
    public boolean addUser(Company company) {
        return client.addUser(company);
    }

    /**
     * @return the instance of client
     */
    public Client getClient() {
        return client;
    }


    public Client getClientByEmail(String email) {
        for (Client c1 : this.array) {
            if (c1.getEmail().equals(email)) {
                return c1;
            }
        }
        return null;
    }

    /**
     * Sorts the client list by TIN number.
     *
     * @return the client list sorted by TIN number
     */
    public List<Client> sortClientListByTin() {

        List<Client> clientListOrder = new ArrayList<>(array);

        for (int i = 0; i < clientListOrder.size(); i++) {
            for (int j = i + 1; j < clientListOrder.size(); j++) {
                Client temp;
                if (Long.parseLong(clientListOrder.get(j).getTinNumber()) < Long.parseLong(clientListOrder.get(i).getTinNumber())) {
                    temp = clientListOrder.get(i);
                    clientListOrder.set(i, clientListOrder.get(j));
                    clientListOrder.set(j, temp);
                }
            }
        }
        return clientListOrder;
    }

    /**
     * Sorts the client list by name.
     *
     * @return the client list sorted by name
     */
    public List<Client> sortClientListByName() {

        List<Client> clientListOrder = new ArrayList<>(array);

        for (int i = 0; i < clientListOrder.size(); i++) {
            for (int j = i + 1; j < clientListOrder.size(); j++) {
                Client temp;
                if (clientListOrder.get(j).getName().compareTo(clientListOrder.get(i).getName()) < 0) {
                    temp = clientListOrder.get(i);
                    clientListOrder.set(i, clientListOrder.get(j));
                    clientListOrder.set(j, temp);
                }
            }
        }
        return clientListOrder;
    }

    public List<Client> getClientList() {
        return this.array;
    }

}
