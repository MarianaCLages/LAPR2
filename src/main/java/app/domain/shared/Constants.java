package app.domain.shared;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    private Constants() {
    }
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
    public static final String ROLE_CLINICALCHEMISTRYTECHNOLOGIST = "CLINICALCHEMISTRYTECHNOLOGIST";
    public static final String ROLE_MEDICALLABTECHNICIIAN = "MEDICALLABTECHNICIIAN";
    public static final String ROLE_LABORATORYCOORDINATOR = "LABORATORYCOORDINATOR";
    public static final String ROLE_SPECIALISTDOCTOR = "SPECIALISTDOCTOR";

    public static final int MAX_CODE = 5;
    public static final int MAX_DESCRIPTION = 15;
    public static final int MAX_COLLECTING_METHODS = 20;
    public static final int MAX_PARAMETER_DESCRIPTION = 20;
    public static final int MAX_CATEGORY_NAME = 15;
    public static final int MAX_PARAMETER_NAME = 8;
    public static final int PHONE_NUMBER_DIGITS = 11;
    public static final int PASSWORD_LENGTH = 10;
    public static final int CC_LENGTH = 16;
    public static final int NHS_LENGTH = 10;
    public static final int TIN_LENGTH = 10;
    public static final int MIN_AGE = 0;
    public static final int MAX_AGE = 150;
    public static final int MAX_CLIENT_NAME = 35;
    public static final int MAX_LAB_NAME = 20;
    public static final int MAX_LAB_ADDRESS = 30;
    public static final int TEST_NHS_CODE = 12;
    public static final int DIAGNOSIS_MAX_LENGHT = 200;

    public static final int ACCESS_KEY = 12345;

    public static final String PARAMS_FILENAME = "src/main/resources/config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
    public static final String FILE = "ClientInformation\\";

    public static final String COMPANY_SER="Ser\\company.ser";


}
