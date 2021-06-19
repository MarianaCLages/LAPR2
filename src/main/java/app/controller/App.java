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
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App  {

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    private Company company;
    private AuthFacade authFacade;

    private App() {
        Properties props = getProperties();
        File f = new File(props.getProperty("serialize.path"));
        if (f.exists() && !f.isDirectory()) {
            this.company = new Company();
        } else
        {
            this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION), props.getProperty("report.hour"), props.getProperty("report.min"), props.getProperty("report.sec"));
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

    public static Properties getProperties() {
        Properties props = new Properties();
        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Many Labs");


        // Read configured values
        InputStream in = null;
        try {
            in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                in.close();
            } catch (IOException err) {

            }
        }

        return props;
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

    private void bootstrap() {
        this.authFacade.addUserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_CLINICALCHEMISTRYTECHNOLOGIST, Constants.ROLE_CLINICALCHEMISTRYTECHNOLOGIST);
        this.authFacade.addUserRole(Constants.ROLE_MEDICALLABTECHNICIIAN, Constants.ROLE_MEDICALLABTECHNICIIAN);
        this.authFacade.addUserRole(Constants.ROLE_LABORATORYCOORDINATOR, Constants.ROLE_LABORATORYCOORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_SPECIALISTDOCTOR, Constants.ROLE_SPECIALISTDOCTOR);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT);


        ParameterCategoryStore parameterCategoryStore = company.getParameterCategoryList();
        ParameterCategory pc1 = parameterCategoryStore.createParameterCategory("12345", "Hemogram");
        parameterCategoryStore.saveParameterCategory();
        ParameterCategory pc2 = parameterCategoryStore.createParameterCategory("12346", "Cholesterol");
        parameterCategoryStore.saveParameterCategory();
        ParameterCategory pc3 = parameterCategoryStore.createParameterCategory("12347", "Covid");
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
        Parameter p4 = new Parameter(Constants.IG_GAN, "COVID", "000", pc3);
        parameterStore.add(p4);
        Parameter p5 = new Parameter("WBC00", "WBC", "White blood cells", pc1);
        parameterStore.add(p5);
        Parameter p6 = new Parameter("PLT00", "PLT", "Platelets", pc1);
        parameterStore.add(p6);
        Parameter p7 = new Parameter("RBC00", "RBC", "Red blood cells", pc1);
        parameterStore.add(p7);


        ClinicalAnalysisLabStore clinicalAnalysisLabStore = company.getClinicalAnalysisLabList();
        ClinicalAnalysisLab cl1 = new ClinicalAnalysisLab("Lab1", "address A", "001DO", "1111111111", "11111111111", ttStore);
        clinicalAnalysisLabStore.add(cl1);
        ClinicalAnalysisLab cl2 = new ClinicalAnalysisLab("Lab2", "address B", "001LN", "1111111112", "11111111112", ttStore);
        clinicalAnalysisLabStore.add(cl2);
        ClinicalAnalysisLab cl3 = new ClinicalAnalysisLab("Lab3", "address C", "001LR", "1111111113", "11111111113", ttStore);
        clinicalAnalysisLabStore.add(cl3);
        ClinicalAnalysisLab cl4 = new ClinicalAnalysisLab("Lab4", "address D", "001MA", "1111111114", "11111111114", ttStore);
        clinicalAnalysisLabStore.add(cl4);
        ClinicalAnalysisLab cl5 = new ClinicalAnalysisLab("Lab5", "address E", "001SO", "1111111115", "11111111115", ttStore);
        clinicalAnalysisLabStore.add(cl5);
        ClinicalAnalysisLab cl6 = new ClinicalAnalysisLab("Lab6", "address F", "001WA", "1111111116", "11111111116", ttStore);
        clinicalAnalysisLabStore.add(cl6);

        ClientStore store = company.getClientList();

        String strDate1 = "07-08-2002";
        String strDate2 = "07-01-2000";
        String strDate3 = "07-08-1995";
        String strDate4 = "27-10-1956";
        String strDate5 = "07-08-1983";


        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = null;
        Date date2 = null;
        Date date3 = null;
        Date date4 = null;
        Date date5 = null;
        try {
            date1 = df.parse(strDate1);
            date2 = df.parse(strDate2);
            date3 = df.parse(strDate3);
            date4 = df.parse(strDate4);
            date5 = df.parse(strDate5);

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


        store.createClient("12345678901", "1234567890123456", "1234567890", "1234567890", date1, 'M', "ze@ze.com", "Zé");
        store.saveClient();
        store.createClient("12345678902", "1234565891123456", "1234567893", "2234567890", date2, 'F', "maria@maria.com", "Maria");
        store.saveClient();
        store.createClient("12345678903", "1234567891123456", "1234567891", "1234567891", date3, 'M', "alberto@alberto.com", "Alberto");
        store.saveClient();
        store.createClient("12345698904", "1234567891123456", "1234566891", "1234567896", date4, 'F', "joana@alberto.com", "Joana");
        store.saveClient();
        store.createClient("12345698905", "1234567891183456", "1234766891", "1234567881", date5, 'M', "manuel@alberto.com", "Manuel");
        store.saveClient();


        TestStore testStore = company.getTestList();
        //covid

        Test t = new Test("1234557890123456", "100000000100", "1234567890", covidTest, testCategories, testParameters1);
        t.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 10, 11, 30));
        t.addTestParameter();
        t.changeState(Constants.SAMPLE_COLLECTED);
        t.addTestResult(Constants.IG_GAN, 1.5);
        t.changeState("VALIDATED");
        testStore.addTest(t);

        Test t1 = new Test("1234567890123457", "100000000001", "1234567890", covidTest, testCategories, testParameters1);
        t1.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 10, 15, 30));
        t1.addTestParameter();
        t1.changeState(Constants.SAMPLE_COLLECTED);
        t1.addTestResult(Constants.IG_GAN, 1.4);
        t1.changeState("VALIDATED");
        testStore.addTest(t1);

        Test t2 = new Test("1234567890123458", "100000000002", "2234567890", covidTest, testCategories, testParameters1);
        t2.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 11, 15, 30));
        t2.addTestParameter();
        t2.changeState(Constants.SAMPLE_COLLECTED);
        t2.addTestResult(Constants.IG_GAN, 0.5);
        t2.changeState(Constants.VALIDATED);
        testStore.addTest(t2);

        Test t3 = new Test("1234567890123459", "100000000003", "1234567896", covidTest, testCategories, testParameters1);
        t3.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 11, 15, 0));
        t3.addTestParameter();
        t3.changeState(Constants.SAMPLE_COLLECTED);
        t3.addTestResult(Constants.IG_GAN, 2);
        t3.changeState(Constants.VALIDATED);
        testStore.addTest(t3);

        Test t4 = new Test("1234567890123460", "100000000004", "1234567891", covidTest, testCategories, testParameters1);
        t4.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 10, 15, 0));
        t4.addTestParameter();
        t4.changeState(Constants.SAMPLE_COLLECTED);
        t4.addTestResult(Constants.IG_GAN, 1);
        t4.changeState(Constants.VALIDATED);
        testStore.addTest(t4);

        Test t5 = new Test("1234567890123461", "100000000006", "1234567881", covidTest, testCategories, testParameters1);
        t5.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 10, 14, 0));
        t5.addTestParameter();
        t5.changeState(Constants.SAMPLE_COLLECTED);
        t5.addTestResult(Constants.IG_GAN, 2.5);
        t5.changeState(Constants.VALIDATED);
        testStore.addTest(t5);

        Test t6 = new Test("1234567890123462", "100000000007", "1234567896", covidTest, testCategories, testParameters1);
        t6.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 12, 14, 0));
        t6.addTestParameter();
        t6.changeState(Constants.SAMPLE_COLLECTED);
        t6.addTestResult(Constants.IG_GAN, 1.5);
        t6.changeState(Constants.VALIDATED);
        testStore.addTest(t6);

        Test t7 = new Test("1234567890123465", "100000000008", "1234567896", covidTest, testCategories, testParameters1);
        t7.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 12, 18, 0));
        t7.addTestParameter();
        t7.changeState(Constants.SAMPLE_COLLECTED);
        t7.addTestResult(Constants.IG_GAN, 1.6);
        t7.changeState(Constants.VALIDATED);
        testStore.addTest(t7);

        Test t8 = new Test("1234567890123466", "100000000009", "1234567896", covidTest, testCategories, testParameters1);
        t8.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 12, 18, 20));
        t8.addTestParameter();
        t8.changeState(Constants.SAMPLE_COLLECTED);
        t8.addTestResult(Constants.IG_GAN, 1.9);
        t8.changeState(Constants.VALIDATED);

        Test t9 = new Test("1234567890123467", "100000000010", "1234567896", covidTest, testCategories, testParameters1);
        t9.setCreatedDate(LocalDateTime.of(2021, Month.JUNE, 12, 16, 50));
        t9.addTestParameter();
        t9.changeState(Constants.SAMPLE_COLLECTED);
        t9.addTestResult(Constants.IG_GAN, 1.79);
        t9.changeState(Constants.VALIDATED);
        testStore.addTest(t9);

        Test t10 = new Test("1234567890123416", "100000000011", "1234567896", covidTest, testCategories, testParameters1);
        t10.setCreatedDate(LocalDateTime.of(2021, Month.MAY, 12, 16, 50));
        t10.addTestParameter();
        t10.changeState(Constants.SAMPLE_COLLECTED);
        t10.addTestResult(Constants.IG_GAN, 1.79);
        t10.changeState(Constants.VALIDATED);
        testStore.addTest(t10);

        //blood

        Test tn = new Test("1234567890123463", "200000000010", "1234567896", bloodTest, testCategories, testParameters);
        tn.addTestParameter();
        tn.changeState(Constants.SAMPLE_COLLECTED);
        tn.addTestResult("MCH00", 15);
        tn.addTestResult("ESR00", 150);
        tn.addTestResult("HB000", 50);
        tn.changeState(Constants.VALIDATED);
        testStore.addTest(tn);

        Test tn1 = new Test("1234567890123464", "200000000009", "1234567881", bloodTest, testCategories, testParameters);
        tn1.addTestParameter();
        tn1.changeState(Constants.SAMPLE_COLLECTED);
        tn1.addTestResult("MCH00", 85);
        tn1.addTestResult("ESR00", 170);
        tn1.addTestResult("HB000", 600);
        tn1.changeState(Constants.VALIDATED);
        testStore.addTest(tn1);
        t4.setSampleCreatedDate(LocalDateTime.of(2021, Month.JUNE, 18, 22, 30));


        Test tn2 = new Test("1234567890123465", "200000000010", "1234567882", bloodTest, testCategories, testParameters);
        tn2.addTestParameter();
        tn2.changeState("SAMPLE_COLLECTED");
        testStore.addTest(tn2);
        t4.setSampleCreatedDate(LocalDateTime.of(2021, Month.JUNE, 18, 22, 30));


        Test tn3 = new Test("1234567890123466", "200000000011", "1234567881", bloodTest, testCategories, testParameters);
        tn3.addTestParameter();
        tn3.changeState("SAMPLE_COLLECTED");
        tn3.addTestResult("MCH00", 60);
        tn3.addTestResult("ESR00", 150);
        tn3.addTestResult("HB000", 60);
        tn3.changeState("SAMPLE_ANALYSED");
        t4.setSampleCreatedDate(LocalDateTime.of(2021, Month.JUNE, 18, 22, 30));


        testStore.addTest(tn3);


        final String pass = "123456";
        this.authFacade.addUserWithRole("Clinical Chemistry Technologist ", "clichetec@lei.sem2.pt", pass, Constants.ROLE_CLINICALCHEMISTRYTECHNOLOGIST);
        this.authFacade.addUserWithRole("Medical Lab Technician ", "melate@lei.sem2.pt", pass, Constants.ROLE_MEDICALLABTECHNICIIAN);
        this.authFacade.addUserWithRole("Receptionist", "recep@lei.sem2.pt", pass, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", pass, Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Specialist Doctor", "specdoc@lei.sem2.pt", pass, Constants.ROLE_SPECIALISTDOCTOR);
        this.authFacade.addUserWithRole("Laboratory Coordinator", "labcord@lei.sem2.pt", pass, Constants.ROLE_LABORATORYCOORDINATOR);

    }
}
