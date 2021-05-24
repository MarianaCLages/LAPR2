package app.domain.shared;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String ROLE_RECEPTIONIST = "Receptionist";
    public static final String ROLE_CLINICALCHEMISTRYTECHNOLOGIST = "Clinical Chemistry Technologist";
    public static final String ROLE_MEDICALLABTECHNICIIAN = "Medical Lab Technician";
    public static final String ROLE_LABORATORYCOORDINATOR = "LaboratoryCoordinator";
    public static final String ROLE_SPECIALISTDOCTOR = "SpecialistDoctor";

    public static final int MAX_CODE = 5;
    public static final int MAX_DESCRIPTION = 15;
    public static final int MAX_COLLECTING_METHODS = 20;
    public static final int MAX_PARAMETER_DESCRIPTION = 20;
    public static final int MAX_CATEGORY_NAME = 10;
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


    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
}
