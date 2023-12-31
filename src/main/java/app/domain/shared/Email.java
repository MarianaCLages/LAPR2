package app.domain.shared;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Class that represents the email that should be sent to the user when he is registered.
 * It will not be implemented any external API
 */
public class Email implements Serializable {
    private Email() {
    }

    /**
     * writes the user's email and the password in a file defined in the constants class
     *
     * @param name     name of the user
     * @param email    email of the user
     * @param password password of the user
     */
    public static void sendPasswordNotification(String name, String email, String password) {
        File passFile = new File(Constants.FILE);
        passFile.mkdir();
        try (FileWriter writer = new FileWriter(passFile + "\\notification.txt")) {
            writer.write("MR/MS " + name + " you were registered with the email " + email + " and your password is " + password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
