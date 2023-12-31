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

    public static final String COMPANY_SER = "Ser\\company.ser";

    //Paths

    public static final String MAIN_SCREEN_UI = "/FXML/MainScreen.fxml";

    public static final String ADMINISTRATOR_UI = "/FXML/AdministratorUI.fxml";
    public static final String SPECIALIST_DOCTOR_UI = "/FXML/SpecialistDoctorUI.fxml";
    public static final String CLIENT_UI = "/FXML/ClientUI.fxml";
    public static final String CLIENT_UPDATE_UI = "/FXML/ClientUpdateUI.fxml";
    public static final String RECEPTIONIST_UI = "/FXML/ReceptionistUI.fxml";
    public static final String LABORATORY_COORDINATOR_UI = "/FXML/LaboratoryCoordinatorUI.fxml";
    public static final String MEDICAL_LAB_TECHINICIAN_UI = "/FXML/MedicalLabTechnicianUI.fxml";
    public static final String CLINICAL_CHEMISTRY_TECHNOLOGIST_UI = "/FXML/ClinicalChemistryTechnologistUI.fxml";

    public static final String TEST_TYPE_UI = "/FXML/TestTypeUI.fxml";
    public static final String PARAMETER_UI = "/FXML/ParameterUI.fxml";
    public static final String EMPLOYEE_UI = "/FXML/EmployeeUI.fxml";
    public static final String CLINICAL_ANALYSIS_LABORATORY_UI = "/FXML/ClinicalAnalysisLaboratoryUI.fxml";
    public static final String NHS_REPORT_UI = "/FXML/NHSReportUI.fxml";
    public static final String PARAMETER_CATEGORY_UI = "/FXML/ParameterCategoryUI.fxml";

    public static final String RECORD_RESULTS_UI = "/FXML/RecordResultsUI.fxml";
    public static final String CONSULT_CLIENT_TESTS_UI = "/FXML/ConsultClientTestsAndResultsUI.fxml";

    public static final String LOGIN_CONTROLLER = "/FXML/Login.fxml";
    public static final String CREDITS_UI = "/FXML/Credits.fxml";

    public static final String VIEW_RESULTS_UI = "/FXML/ViewResultsUI.fxml";
    public static final String IMPORT_TEST_UI = "/FXML/ImportTest.fxml";

    public static final String COMPANY_PERFORMANCE_UI = "/FXML/CompanyPerformanceUI.fxml";
    public static final String PERFORMANCE_GRAPH_UI = "/FXML/PerformanceGraph.fxml";

    public static final String SIMPLE_LINEAR_REGRESSION_UI = "/FXML/SimpleLinearRegression.fxml";
    public static final String MULTI_LINEAR_REGRESSION_UI = "/FXML/MultiLinearRegression.fxml";

    public static final String SIMPLE_LINEAR_REGRESSION = "Simple Linear Regression";
    public static final String MULTI_LINEAR_REGRESSION = "Multi Linear Regression";

    public static final String BENCHMARK_ALGORITHM = "Benchmark Algorithm";
    public static final String BRUTEFORCE_ALGORITHM = "BruteForce Algorithm";

    public static final String BENCHMARK_ALGORITHM_PATH = "app.domain.model.MaxSumAdapterBenchmark";
    public static final String BRUTEFORCE_ALGORITHM_PATH = "app.domain.model.MaxSumAdapterBruteForce";

    public static final String ERROR_BLANK_CONTAINERS = "Please enter valid information (Don't leave blank containers!)";

    public static final String COVID_TESTS = "Covid-19 tests";
    public static final String MEAN_AGE = "Mean age";

    public static final String CLIENT_LIST_TIN = "TIN number";
    public static final String CLIENT_LIST_NAME = "Name";

    public static final double VALID_COVID_PARAMETER_VALUE = 1.4;

    public static final String CREATED = "CREATED";
    public static final String SAMPLE_COLLECTED = "SAMPLE_COLLECTED";
    public static final String VALIDATED = "VALIDATED";

    public static final String IG_GAN = "IgGAN";
    public static final String COV19 = "COV19";

    public static final String DAY = "Day";
    public static final String WEEK = "Week";
    public static final String MONTH = "Month";
    public static final String YEAR = "Year";

    public static final String MCH00 ="MCH00";
    public static final String ESR00 = "ESR00";
    public static final String WBC00 = "WBC00";
    public static final String PLT00 = "PLT00";
    public static final String RBC00 = "RBC00";
    public static final String HB000 = "HB000";

    public static final String BL000 ="BL000";
    public static final String NUMBER ="1234567896";
    public static final String NUMBER_2="1234567881";

}
