package app.domain.stores;

import app.domain.model.Client;
import app.domain.model.Company;
import auth.AuthFacade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that represents an List of all the clients in the system
 */
public class ClientStore {
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
    public void CreateClient(String phoneNumber, String cc, String nhs, String tinNumber, Date birthDate, char sex, String email, String name) {
        if (sex == 'M' || sex == 'F') {
            this.client = new Client(phoneNumber, cc, nhs, tinNumber, birthDate, sex, email, name);
        } else {
            this.client = new Client(phoneNumber, cc, nhs, tinNumber, birthDate, email, name);
        }
    }

    /**
     * Checks if a instance of object Client is valid. An instance of a client is valid if it is not null, if dont already exists or if the email and phone number of the instance are not unique
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

    public boolean exists(String tin){
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
     * @return boolean value that represents the success of the operation
     */
    public boolean addUser() {
        return client.addUser();
    }

    /**
     * @return the instance of client
     */
    public Client getClient() {
        return client;
    }
}
