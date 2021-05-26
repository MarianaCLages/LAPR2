package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
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
        parameterStore.createParameter("MCH00", "MCH", "Mean Haemoglobin", pc1);
        parameterStore.saveParameter();
        parameterStore.createParameter("ESR00", "ESR", "Erythrocyte Rate", pc2);
        parameterStore.saveParameter();
        parameterStore.createParameter("HB000", "HB", "Haemoglobin", pc1);
        parameterStore.saveParameter();

        ParameterStore parameterStore1 = new ParameterStore();
        parameterStore1.createParameter("IgGAN", "COVID", "000", pc1);
        parameterStore1.saveParameter();

        ClientStore store = company.clientList();

        String strDate = "07-08-2002";

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        store.CreateClient("12345678901","1234567890123456","1234567890","1234567890",date,'M',"ze@ze.com","Zé");
        store.saveClient();
        TestStore testStore = company.getTestList();

        testStore.createTest("100000000000", "1234567890", bloodTest, parameterCategoryStore, parameterStore);
        testStore.createTest("100000000001", "1234567890", covidTest, parameterCategoryStore, parameterStore1);


        this.authFacade.addUserWithRole("Receptionist", "recep@lei.sem2.pt", "123456", Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456", Constants.ROLE_ADMIN);
    }
}
