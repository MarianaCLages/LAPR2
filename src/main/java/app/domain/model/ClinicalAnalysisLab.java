package app.domain.model;

import org.apache.commons.lang3.StringUtils;

public class ClinicalAnalysisLab {
    private String name;
    private String address;
    private String id;
    private String tin;
    private String phoneNumber;
    private TestTypeStore tType;

    public ClinicalAnalysisLab(String name, String address, String id, String tin, String phoneNumber, TestTypeStore tType) {
        checkNameRules(name);
        checkAdressRules(address);
        checkPhoneNumberRules(phoneNumber);
        checkLabIDRules(id);
        checkTestTypeStoreRules(tType);
        this.name = name;
        this.address = address;
        this.id = id;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
    }

    private void checkTestTypeStoreRules(TestTypeStore tType) {
        if (tType.isEmpty())
            throw new IllegalArgumentException("Collecting method cannot be blank.");
    }

    public ClinicalAnalysisLab(TestType testType){
        this.tType = tType;
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param name name of the laboratory
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (name.length() < 15)
            throw new IllegalArgumentException("Name must have at least 15 chars.");
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param address address of the laboratory
     */
    private void checkAdressRules(String address) {
        if (StringUtils.isBlank(address))
            throw new IllegalArgumentException("Address cannot be blank.");
        if (address.length() > 30)
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
        if (phoneNumber.length() != 11)
            throw new IllegalArgumentException("Phone number must have 11 chars.");
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param tin TIN of the laboratory
     */
    private void checkTINNumberRules(String tin) {
        if (StringUtils.isBlank(tin))
            throw new IllegalArgumentException("TIN cannot be blank.");
        if (tin.length() != 10)
            throw new IllegalArgumentException("TIN must have 10 chars.");
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param id laboratory's ID
     */
    private void checkLabIDRules(String id) {
        if (StringUtils.isBlank(id))
            throw new IllegalArgumentException("Laboratory ID cannot be blank.");
        if (id.length() != 5)
            throw new IllegalArgumentException("Laboratory ID must have 5 chars.");
    }

}