package app.domain.model;

import app.domain.shared.Constants;
import app.domain.stores.TestTypeStore;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Class that represents an Clinical Analysis Lab
 */
public class ClinicalAnalysisLab implements Serializable {
    private final TestTypeStore tType;
    private String name;
    private String address;
    private String id;
    private String tin;
    private String phoneNumber;

    /**
     * Constructor of the ClinicalAnalysisLab, it calls methods in order to validate the parameters
     *
     * @param name        name of the Clinical Analysis Lab
     * @param address     address of the Clinical Analysis Lab
     * @param id          id of Clinical Analysis Lab
     * @param tin         TIN of Clinical Analysis Lab
     * @param phoneNumber Phone Number Clinical Analysis Lab
     * @param tType       list of Test Types associated with the test
     */

    public ClinicalAnalysisLab(String name, String address, String id, String tin, String phoneNumber, TestTypeStore tType) {
        checkNameRules(name);
        checkAddressRules(address);
        checkPhoneNumberRules(phoneNumber);
        checkLabIDRules(id);
        checkTINNumberRules(tin);
        checkTestTypeRules(tType);
        this.name = name;
        this.address = address;
        this.id = id;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.tType = tType;
    }

    public ClinicalAnalysisLab(TestTypeStore testType) {
        this.tType = testType;
    }
    /**
     * Checks if the string that is received is only numerical using the class NumberUtils
     *
     * @param i The String that is going to be checked
     * @return boolean value that is positive if the parameter is only numerical
     */
    private boolean checkIfIsNumerical(String i) {
        return !(i.matches("[0-9]+"));

    }
    /**
     * This method checks if the list of Types of tests provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param tType list of Test Types associated with the test
     */
    private void checkTestTypeRules(TestTypeStore tType) {
        if (tType == null)
            throw new IllegalArgumentException("There should exist test types associated with this clinical lab");
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param id laboratory's ID
     */
    private void checkLabIDRules(String id) {
        if (StringUtils.isBlank(id))
            throw new IllegalArgumentException("Laboratory ID cannot be blank.");
        if (id.length() != Constants.MAX_CODE)
            throw new IllegalArgumentException("Laboratory ID must have 5 chars.");
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param name name of the laboratory
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (name.length() > Constants.MAX_LAB_NAME)
            throw new IllegalArgumentException("Name must have at maximum 20 chars.");
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param address address of the laboratory
     */
    private void checkAddressRules(String address) {
        if (StringUtils.isBlank(address))
            throw new IllegalArgumentException("Address cannot be blank.");
        if (address.length() > Constants.MAX_LAB_ADDRESS)
            throw new IllegalArgumentException("Address has, at maximum, 30 chars.");
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param phoneNumber phone number of the laboratory
     */
    private void checkPhoneNumberRules(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber))
            throw new IllegalArgumentException("Phone number cannot be blank.");
        if (phoneNumber.length() != Constants.PHONE_NUMBER_DIGITS)
            throw new IllegalArgumentException("Phone number must have 11 chars.");
        if(checkIfIsNumerical(phoneNumber)){
            throw new IllegalArgumentException("Phone Number only accepts numbers.");
        }
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param tin TIN of the laboratory
     */
    private void checkTINNumberRules(String tin) {
        if (StringUtils.isBlank(tin))
            throw new IllegalArgumentException("TIN cannot be blank.");
        if (tin.length() != Constants.TIN_LENGTH)
            throw new IllegalArgumentException("TIN must have 10 chars.");
        if(checkIfIsNumerical(tin)){
            throw new IllegalArgumentException("TIN only accepts numbers.");
        }

    }

    /**
     * @return A string with the format ClinicalAnalysisLab: name= name, address= address, id= id, tin=tType, phonenumber= phoneNumber, typetest= (list of tests);
     */
    @Override
    public String toString() {
        return "ClinicalAnalysisLab: " +
                "name=" + this.name +
                ", address=" + this.address +
                ", id=" + this.id +
                ", tin=" + this.tin +
                ", phonenumber=" + this.phoneNumber +
                ", typetest=" + this.tType.toString();
    }

    public String getId() {
        return this.id;
    }
}
