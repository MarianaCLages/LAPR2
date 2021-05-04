package app.domain.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;
import java.text.Normalizer;

public class Client {

    private String phoneNumber;
    private String cc;
    private String nhs;
    private String tinNumber;
    private Date birthDate;
    private String sex;
    private String email;
    private String name;


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

    private boolean checkIfIsNumerical(String i) {
        return NumberUtils.isCreatable(i);
    }

    private void checkPhoneNumber(String phoneNumber) {
        if (!checkIfIsNumerical(phoneNumber)) {
            throw new IllegalArgumentException("Phone Number must be a number");
        }
        if (phoneNumber.length() != 11) {
            throw new IllegalArgumentException("Phone Number must have 11 digits");
        }
    }

    private void checkCc(String cc) {
        if (!checkIfIsNumerical(cc)) {
            throw new IllegalArgumentException("Citizen Card must be a number");
        }
        if (cc.length() != 16) {
            throw new IllegalArgumentException("Citizen Card must have 16 digits");
        }
    }

    private void checkNhs(String nhs) {
        if (!checkIfIsNumerical(nhs)) {
            throw new IllegalArgumentException("National Health System must be a number");
        }
        if (nhs.length() != 10) {
            throw new IllegalArgumentException("National Health System number must have 10 digits");
        }
    }

    private void checkTin(String tif) {
        if (!checkIfIsNumerical(tif)) {
            throw new IllegalArgumentException("Tax Identification Number must be a number");
        }
        if (tif.length() != 10) {
            throw new IllegalArgumentException("Tax Identification Number number must have 10 digits");
        }
    }

    private int checkAge(Date birthDate) {
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Transition from object Date to object LocalDate retrieved from https://www.baeldung.com/java-date-to-localdate-and-localdatetime
        return Period.between(date, LocalDate.now()).getYears(); // Inspired in https://stackoverflow.com/a/1116138
    }

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
     * @author Paulo Maio <pam@isep.ipp.pt>
     * @param email
     */

    private void checkEmailRules(String email) {

        if (StringUtils.isBlank(email))
            throw new IllegalArgumentException("Code cannot be blank.");

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if(!pat.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid Email.");
        }
    }

    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");

        name = name.toLowerCase();
        name = Normalizer.normalize(name, Normalizer.Form.NFD);
        name = name.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        char[] charArray = name.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= 'a' && c <= 'z')) {
                throw new IllegalArgumentException("Name only accepts letters");
            }
        }

        if (name.length() >= 35){
            throw new IllegalArgumentException("Name must have maximum of 35 characters");
        }
    }


    @Override
    public String toString() {
        return "Client: " + "phoneNumber= " + phoneNumber + ", cc='" + cc +", nhs='" + nhs + ", tinNumber='" + tinNumber + ", birthDate=" + birthDate +", sex='" + sex +", email='" + email +", name='" + name ;
    }
}
