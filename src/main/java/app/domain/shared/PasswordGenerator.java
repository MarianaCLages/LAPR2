package app.domain.shared;

import app.domain.shared.Constants;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;

public class PasswordGenerator implements Serializable {
    private PasswordGenerator() {
    }

    /**
     * This method generates a new random password with alphanumeric characters using the class RandomStringUtils
     *
     * @return random string representing the client password
     */
    public static String getPassword() {
        return RandomStringUtils.randomAlphanumeric(Constants.PASSWORD_LENGTH);
    }
}
