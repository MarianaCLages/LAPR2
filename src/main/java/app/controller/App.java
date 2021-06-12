package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.stores.*;
import auth.AuthFacade;
import auth.UserSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    private Company company;
    private AuthFacade authFacade;

    private App() {
        Properties props = getProperties();
        File f = new File(props.getProperty("serialize.path"));
       /* if (f.exists() && !f.isDirectory()) {
            this.company = new Company();
        } else*/ {
            this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION),props.getProperty("report.hour"),props.getProperty("report.min"),props.getProperty("report.sec"));
            this.authFacade = this.company.getAuthFacade();
            bootstrap();
        }
        company.saveCompany();

        this.authFacade = this.company.getAuthFacade();


    }

    public static App getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }

    public Company getCompany() {
        return this.company;
    }

    public UserSession getCurrentUserSession() {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd) {
        return this.authFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        this.authFacade.doLogout();
    }

    public static Properties getProperties() {
        Properties props = new Properties();
        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Many Labs");


        // Read configured values
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return props;
    }

    private void bootstrap() {
        this.authFacade.addUserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_CLINICALCHEMISTRYTECHNOLOGIST, Constants.ROLE_CLINICALCHEMISTRYTECHNOLOGIST);
        this.authFacade.addUserRole(Constants.ROLE_MEDICALLABTECHNICIIAN, Constants.ROLE_MEDICALLABTECHNICIIAN);
        this.authFacade.addUserRole(Constants.ROLE_LABORATORYCOORDINATOR, Constants.ROLE_LABORATORYCOORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_SPECIALISTDOCTOR, Constants.ROLE_SPECIALISTDOCTOR);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT);


        ParameterCategoryStore parameterCategoryStore = company.getParameterCategoryList();
        ParameterCategory pc1 = parameterCategoryStore.CreateParameterCategory("12345", "Hemogram");
        parameterCategoryStore.saveParameterCategory();
        ParameterCategory pc2 = parameterCategoryStore.CreateParameterCategory("12346", "Cholesterol");
        parameterCategoryStore.saveParameterCategory();
        ParameterCategory pc3 = parameterCategoryStore.CreateParameterCategory("12347", "Covid");
        parameterCategoryStore.saveParameterCategory();

        TestTypeStore ttStore = company.getTestTypeList();
        TestType bloodTest = new TestType("BL000", "Blood", "Needle", parameterCategoryStore);
        TestType covidTest = new TestType("COV19", "Covid", "Swab", parameterCategoryStore);

        ttStore.add(bloodTest);
        ttStore.add(covidTest);

        ParameterStore parameterStore = company.getParameterList();
        Parameter p1 = new Parameter("MCH00", "MCH", "Mean Haemoglobin", pc1);
        parameterStore.add(p1);
        Parameter p2 = new Parameter("ESR00", "ESR", "Erythrocyte Rate", pc2);
        parameterStore.add(p2);
        Parameter p3 = new Parameter("HB000", "HB", "Haemoglobin", pc1);
        parameterStore.add(p3);
        Parameter p4 = new Parameter("IgGAN", "COVID", "000", pc3);
        parameterStore.add(p4);
        Parameter p5 = new Parameter("WBC00", "WBC", "White blood cells", pc1);
        parameterStore.add(p5);
        Parameter p6 = new Parameter("PLT00", "PLT", "Platelets", pc1);
        parameterStore.add(p6);
        Parameter p7 = new Parameter("RBC00", "RBC", "Red blood cells", pc1);
        parameterStore.add(p7);



        ClinicalAnalysisLabStore clinicalAnalysisLabStore = company.getClinicalAnalysisLabList();
        ClinicalAnalysisLab cl1 = new ClinicalAnalysisLab("Lab1", "address A", "001DO", "1111111111", "11111111111",ttStore);
        clinicalAnalysisLabStore.add(cl1);
        ClinicalAnalysisLab cl2 = new ClinicalAnalysisLab("Lab2", "address B", "001LN", "1111111112", "11111111112",ttStore);
        clinicalAnalysisLabStore.add(cl2);
        ClinicalAnalysisLab cl3 = new ClinicalAnalysisLab("Lab3", "address C", "001LR", "1111111113", "11111111113",ttStore);
        clinicalAnalysisLabStore.add(cl3);
        ClinicalAnalysisLab cl4 = new ClinicalAnalysisLab("Lab4", "address D", "001MA", "1111111114", "11111111114", ttStore);
        clinicalAnalysisLabStore.add(cl4);
        ClinicalAnalysisLab cl5 = new ClinicalAnalysisLab("Lab5", "address E", "001SO", "1111111115", "11111111115", ttStore);
        clinicalAnalysisLabStore.add(cl5);
        ClinicalAnalysisLab cl6 = new ClinicalAnalysisLab("Lab6", "address F", "001WA", "1111111116", "11111111116", ttStore);
        clinicalAnalysisLabStore.add(cl6);

        ClientStore store = company.getClientList();

        String strDate = "07-08-2002";

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<ParameterCategory> testCategories = new ArrayList<>();
        testCategories.add(pc1);

        List<Parameter> testParameters = new ArrayList<>();
        testParameters.add(p1);
        testParameters.add(p2);
        testParameters.add(p3);

        List<Parameter> testParameters1 = new ArrayList<>();
        testParameters1.add(p4);


        store.CreateClient("12345678901", "1234567890123456", "1234567890", "1234567890", date, 'M', "ze@ze.com", "Zé");
        store.saveClient();
        store.CreateClient("12345678902", "1234567891123456", "1234567893", "2234567890", date, 'F', "maria@maria.com", "Maria");
        store.saveClient();

        TestStore testStore = company.getTestList();

        testStore.createTest("100000000000", "1234567890", bloodTest, testCategories, testParameters);
        testStore.saveTest();
        testStore.getT().changeState("VALIDATED");
        testStore.createTest("100000000001", "2234567890", covidTest, testCategories, testParameters1);
        testStore.saveTest();
        testStore.getT().changeState("VALIDATED");
        testStore.createTest("100000000002", "1234567890", covidTest, testCategories, testParameters);
        testStore.saveTest();
        testStore.getT().changeState("VALIDATED");

        final String pass = "123456";
        this.authFacade.addUserWithRole("Clinical Chemistry Technologist ", "clichetec@lei.sem2.pt", pass, Constants.ROLE_CLINICALCHEMISTRYTECHNOLOGIST);
        this.authFacade.addUserWithRole("Medical Lab Technician ", "melate@lei.sem2.pt", pass, Constants.ROLE_MEDICALLABTECHNICIIAN);
        this.authFacade.addUserWithRole("Receptionist", "recep@lei.sem2.pt", pass, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", pass, Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Specialist Doctor", "specdoc@lei.sem2.pt", pass, Constants.ROLE_SPECIALISTDOCTOR);
        this.authFacade.addUserWithRole("Laboratory Coordinator", "labcord@lei.sem2.pt", pass, Constants.ROLE_LABORATORYCOORDINATOR);

    }
}
