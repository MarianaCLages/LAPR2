package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Employee {
    private String name;
    private String address;
    private String phonenumber;
    private String email;
    private String SOC;
    private Role role;
    private String employeeID;


    public Employee(String employeeID, String name, String address, String phonenumber, String email, String SOC, Role role) {
        checkNameRules(name);
        checkAddressRules(address);
        checkPhoneNumberRules(phonenumber);
        checkEmailRules(email);
        checkSOCRules(SOC);
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.SOC = SOC;
        this.role = role;
        this.employeeID = employeeID;

    }




    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");

        name = name.toLowerCase();
        char[] charArray = name.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= 'a' && c <= 'z')) {
                throw new IllegalArgumentException("Name only accepts letters");
            }
        }
    }

    private void checkAddressRules(String address) {
        if (StringUtils.isBlank(address))
            throw new IllegalArgumentException("Address cannot be blank.");
    }

    private void checkPhoneNumberRules(String phonenumber) {
        if (StringUtils.isBlank(phonenumber))
            throw new IllegalArgumentException("Phonenumber cannot be blank.");
        if (phonenumber.length() != 11)
            throw new IllegalArgumentException("Phonenumber must have 11 chars.");

        char[] charArray = phonenumber.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= '0' && c <= '9')) {
                throw new IllegalArgumentException("Phonenumber only accepts numbers");
            }
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
            throw new IllegalArgumentException("Email cannot be blank.");

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if(!pat.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid Email.");
        }
    }





    private void checkSOCRules(String SOC) {
        if (StringUtils.isBlank(SOC))
            throw new IllegalArgumentException("SOC cannot be blank.");

        char[] charArray = SOC.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= '0' && c <= '9')) {
                throw new IllegalArgumentException("SOC only accepts numbers");
            }
        }
    }




    @Override
    public String toString() {
        return "Employee:" +
                "ID=" + employeeID +
                ", name=" + name +
                ", address=" + address +
                ", phonenumber=" + phonenumber +
                ", email=" + email +
                ", SOC=" + SOC +
                ", Role="+ role ;

    }








}
