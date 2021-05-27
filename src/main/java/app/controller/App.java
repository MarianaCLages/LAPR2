package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.stores.*;
import auth.AuthFacade;
import auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
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

    private Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Many Labs");


        // Read configured values
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {

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
        this.authFacade.addUserRole(Constants.ROLE_CLIENT,Constants.ROLE_CLIENT);


        ParameterCategoryStore parameterCategoryStore = company.getParameterCategoryList();
        ParameterCategory pc1 = parameterCategoryStore.CreateParameterCategory("12345", "categoria");
        parameterCategoryStore.saveParameterCategory();
        ParameterCategory pc2 = parameterCategoryStore.CreateParameterCategory("12346", "cat");
        parameterCategoryStore.saveParameterCategory();


        TestTypeStore ttStore = company.getTestTypeList();
        TestType bloodTest = new TestType("BL000", "Blood Test", "Needle", parameterCategoryStore);
        TestType covidTest = new TestType("COV19", "Covid Test", "Swap", parameterCategoryStore);

        ttStore.add(bloodTest);

        ttStore.add(covidTest);
        ParameterStore parameterStore = company.getParameterList();
        Parameter p1 = new  Parameter("MCH00", "MCH", "Mean Haemoglobin", pc1);
        parameterStore.add(p1);
        Parameter p2 = new  Parameter("ESR00", "ESR", "Erythrocyte Rate", pc2);
        parameterStore.add(p2);
        Parameter p3 = new  Parameter("HB000", "HB", "Haemoglobin", pc1);
        parameterStore.add(p3);


        Parameter p4 = new  Parameter("IgGAN", "COVID", "000", pc1);
        parameterStore.add(p4);

        ClientStore store = company.clientList();

        String strDate = "07-08-2002";

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ParameterCategoryList testCategories = new  ParameterCategoryList();
        testCategories.add(pc1);

        ParameterList testParameters = new  ParameterList();
        testParameters.add(p1);
        testParameters.add(p2);
        testParameters.add(p3);

        ParameterList testParameters1 = new  ParameterList();
        testParameters1.add(p4);


        store.CreateClient("12345678901","1234567890123456","1234567890","1234567890",date,'M',"ze@ze.com","Zé");
        store.saveClient();
        TestStore testStore = company.getTestList();

        testStore.createTest("100000000000", "1234567890", bloodTest, testCategories, testParameters);
        testStore.saveTest();
        testStore.createTest("100000000001", "1234567890", covidTest, testCategories, testParameters1);
        testStore.saveTest();

        this.authFacade.addUserWithRole("Clinical Chemistry Technologist ", "clichetec@lei.sem2.pt", "123456", Constants.ROLE_CLINICALCHEMISTRYTECHNOLOGIST);
        this.authFacade.addUserWithRole("Medical Lab Technician ", "melate@lei.sem2.pt", "123456", Constants.ROLE_MEDICALLABTECHNICIIAN);
        this.authFacade.addUserWithRole("Receptionist", "recep@lei.sem2.pt", "123456", Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456", Constants.ROLE_ADMIN);
    }
}
