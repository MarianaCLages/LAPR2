package app.domain.model;

import app.domain.shared.Constants;
import auth.AuthFacade;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

public class Client {

    private String phoneNumber;
    private String cc;
    private String nhs;
    private String tinNumber;
    private Date birthDate;
    private String sex;
    private String email;
    private String name;


    /**
     * Constructor of the ParameterCategory specifying the sex of the Client, it calls methods in order to validate the parameters
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

    public Client(String phoneNumber, String cc, String nhs, String tinNumber, Date birthDate, char sex, String email, String name) {
        checkPhoneNumber(phoneNumber);
        checkCc(cc);
        checkNhs(nhs);
        checkTin(tinNumber);
        checkBirthDate(birthDate, checkAge(birthDate));
        checkEmailRules(email);
        checkNameRules(name);
        this.phoneNumber = phoneNumber;
        this.cc = cc;
        this.nhs = nhs;
        this.tinNumber = tinNumber;
        this.birthDate = birthDate;

        if (sex == 'M') {
            this.sex = "Male";
        } else {
            if (sex == 'F') {
                this.sex = "Female";
            } else {
                throw new IllegalArgumentException("Sex must be 'M' or 'F' ");
            }
        }

        this.email = email;
        this.name = name;
    }

    /**
     * Constructor of the ParameterCategory not specifying the sex of the Client, it calls methods in order to validate the parameters
     *
     * @param phoneNumber unique phone number that belongs to client
     * @param cc          citizen card number of the client
     * @param nhs         national health system number of the client
     * @param tinNumber   tax identification number of the client
     * @param birthDate   birth date of the client
     * @param email       unique email that belongs to client
     * @param name        name of the client
     */
    public Client(String phoneNumber, String cc, String nhs, String tinNumber, Date birthDate, String email, String name) {
        checkPhoneNumber(phoneNumber);
        checkCc(cc);
        checkNhs(nhs);
        checkTin(tinNumber);
        checkBirthDate(birthDate, checkAge(birthDate));
        checkEmailRules(email);
        checkNameRules(name);
        this.phoneNumber = phoneNumber;
        this.cc = cc;
        this.nhs = nhs;
        this.tinNumber = tinNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.name = name;
    }

    /**
     * Checks if the string that is received is only numerical using the class NumberUtils
     *
     * @param i The String that is going to be checked
     * @return boolean value that is positive if the parameter is only numerical
     */
    private boolean checkIfIsNumerical(String i) {
        return NumberUtils.isCreatable(i);
    }

    /**
     * Checks if the string that is received meets the requirements of the phone number, if not throws Exceptions
     *
     * @param phoneNumber unique phone number that belongs to client
     */
    private void checkPhoneNumber(String phoneNumber) {
        if (!checkIfIsNumerical(phoneNumber)) {
            throw new IllegalArgumentException("Phone Number must be a number");
        }
        if (phoneNumber.length() != 11) {
            throw new IllegalArgumentException("Phone Number must have 11 digits");
        }
    }

    /**
     * Checks if the string that is received meets the requirements of the citizen card number, if not throws Exceptions
     *
     * @param cc citizen card number of the client
     */
    private void checkCc(String cc) {
        if (!checkIfIsNumerical(cc)) {
            throw new IllegalArgumentException("Citizen Card must be a number");
        }
        if (cc.length() != 16) {
            throw new IllegalArgumentException("Citizen Card must have 16 digits");
        }
    }

    /**
     * Checks if the string that is received meets the requirements of the national health system number, if not throws Exceptions
     *
     * @param nhs national health system number of the client
     */
    private void checkNhs(String nhs) {
        if (!checkIfIsNumerical(nhs)) {
            throw new IllegalArgumentException("National Health System must be a number");
        }
        if (nhs.length() != 10) {
            throw new IllegalArgumentException("National Health System number must have 10 digits");
        }
    }

    /**
     * Checks if the string that is received meets the requirements of the tax identification number, if not throws Exceptions
     *
     * @param tin tax identification number of the client
     */
    private void checkTin(String tin) {
        if (!checkIfIsNumerical(tin)) {
            throw new IllegalArgumentException("Tax Identification Number must be a number");
        }
        if (tin.length() != 10) {
            throw new IllegalArgumentException("Tax Identification Number number must have 10 digits");
        }
    }

    /**
     * Receives the birthdate as an instance of Date class, converts the instance for an instance of LocalDate and uses the Class Period to calculate the time difference between the today's date and the birthdate received by parameter
     *
     * @param birthDate Date instance to calculate time until today's Date
     * @return integer that represents the time difference in years
     */
    private int checkAge(Date birthDate) {
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Transition from object Date to object LocalDate retrieved from https://www.baeldung.com/java-date-to-localdate-and-localdatetime
        return Period.between(date, LocalDate.now()).getYears(); // Inspired in https://stackoverflow.com/a/1116138
    }

    /**
     * Checks if the birthdate meets all the requirements of the birthdate of client, if not throws Exceptions
     *
     * @param birthDate birth date of the client
     * @param age       age of client calculated by checkAge method
     */

    private void checkBirthDate(Date birthDate, int age) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth Date cannot be null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Birth Date cannot be in the future");
        }
        if (age > 150) {
            throw new IllegalArgumentException("Age cannot be more than 150");
        }

    }

    /**
     * This methode checks if email is correct.
     * Retrieved by professor Paulo Maio code of template of the project.
     *
     * @param email unique email that belongs to client
     * @author Paulo Maio <pam@isep.ipp.pt>
     */

    private void checkEmailRules(String email) {

        if (StringUtils.isBlank(email))
            throw new IllegalArgumentException("Code cannot be blank.");

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (!pat.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid Email.");
        }
    }

    /**
     * Checks if the string that is received meets the requirements of the name, if not throws Exceptions. Replace all the capital letters to lower letters, cut off all the special characters and spaces and checks if all the remain characters are letters
     *
     * @param name String representing the name of the client that is going to be checked
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");

        name = name.toLowerCase();
        name = Normalizer.normalize(name, Normalizer.Form.NFD);
        name = name.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        name = name.replaceAll(" ", "");
        char[] charArray = name.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= 'a' && c <= 'z')) {
                throw new IllegalArgumentException("Name only accepts letters");
            }
        }

        if (name.length() > 35) {
            throw new IllegalArgumentException("Name must have maximum of 35 characters");
        }
    }

    /**
     * @return A string with the format "Client: phoneNumber=  phoneNumber, cc= cc, nhs=  nhs, tinNumber= tinNumber, birthDate= date, sex= sex, email=  email, name= name"
     */
    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(this.birthDate.getTime());

        return "Client: " + "phoneNumber= " + phoneNumber + ", cc= " + cc + ", nhs= " + nhs + ", tinNumber= " + tinNumber + ", birthDate= " + date + ", sex= " + sex + ", email= " + email + ", name= " + name;
    }

    /**
     * @return String representing the phone number of the client
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return String representing the email of the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method generates a new random password with alphanumeric characters using the class RandomStringUtils
     *
     * @return random string representing the client password
     */
    private String getPassword() {
        int lenght = 10;

        return RandomStringUtils.randomAlphanumeric(lenght);
    }

    /**
     * Adds a new user to the system with the role of the client using the getPassword method to create the user's password
     * @param company instance of company class in order to be able to get the AuthFacade class that is associated with the system
     * @return a boolean value representing the success of the operation
     */

    public boolean addUser(Company company) {
        boolean success = false;
        String password = getPassword();
        AuthFacade authFacade = company.getAuthFacade();
        success = authFacade.addUserWithRole(this.name, this.email, password, Constants.ROLE_CLIENT);
        if (success) {
            Email mail = new Email(this.email, password);

        }
        return success;
    }

}
