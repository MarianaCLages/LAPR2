package app.controller;


import app.domain.model.Company;
import app.domain.stores.ClientStore;

import java.util.Date;

/**
 * Controller of the UserStory : Register a new Client
 */
public class ClientController {

    private Company company;
    private ClientStore store;

    /**
     * Constructor of the class, gets an instance of the company class
     */
    public ClientController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor of the class, receives an instance of the company class
     *
     * @param company instance of Company
     */
    public ClientController(Company company) {
        this.company = company;
        store = company.getClientList();

    }

    /**
     * Creates a new Client instance, firstly creates a instance of ClientStore and then call the method of this instance that creates the Client instance
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
    public void createClient(String cc, String nhs, Date birthDate, char sex, String tinNumber, String phoneNumber, String email, String name) {
        store.CreateClient(phoneNumber, cc, nhs, tinNumber, birthDate, sex, email, name);
    }

    /**
     * @return String that represents the Client instance
     */
    public String getClient() {
        return store.getClient().toString();
    }

    /**
     * Calls the method of the instance of ClientStore that saves the Client instance in the ArrayList, after saving Calls the method of the instance of ClientStore that adds an new user to the system
     *
     * @return success of the operation
     */
    public boolean saveClient() {
        boolean saved = false;
        saved = store.saveClient();
        store.addUser();
        return saved;
    }


}

