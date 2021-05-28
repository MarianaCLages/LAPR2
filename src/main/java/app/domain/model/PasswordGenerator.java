package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.RandomStringUtils;

public class PasswordGenerator {
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
