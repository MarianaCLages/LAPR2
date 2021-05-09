package app.domain.model;

/**
 * Class that represents the email that should be sent to the user when he is registered.
 * It will not be implemented any external API
 */
public class Email {
    private String address;
    private String password;

    public Email(String address, String password) {
        this.address = address;
        this.password = password;
    }

}
