package app.domain.model;

import app.controller.App;
import app.domain.shared.Constants;
import app.domain.shared.Email;
import app.domain.shared.PasswordGenerator;
import app.domain.stores.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class that represents the Tests that are going to be Imported from the CSV file
 */
public class ImportTests {

    int errorcount = 0;
    TestTypeStore ttstore;
    ClientStore cstore;
    ClinicalAnalysisLabStore store;
    ParameterCategoryStore pcstore;
    ParameterStore pstore;
    TestStore tstore;
    List<String> testFileList = new ArrayList<>();

    /**
     * Constructor of ImportTests, it calls methods in order to validate the parameters
     */
    public ImportTests() {
        App app = App.getInstance();
        Company company = app.getCompany();
        cstore = company.getClientList();
        pcstore = company.getParameterCategoryList();
        tstore = company.getTestList();
        ttstore = company.getTestTypeList();
        pstore = company.getParameterList();
        store = company.getClinicalAnalysisLabList();

    }

    /**
     * This method reads a test from the CSV file
     * @param filepath path of the CSV file
     */
    public void readTestFromCSV(String filepath) {


        Path path = Paths.get(filepath);
        String firstLine1 = "Test_Code;NHS_Code;Lab_ID;CitizenCard_Number;NHS_Number;TIN;BirthDay;PhoneNumber;Name;E-mail ;Address;TestType;Category;HB000;WBC00;PLT00;RBC00;Category;HDL00;Category;IgGAN;Test_Reg_DateHour;Test_Chemical_DateHour;Test_Doctor_DateHour;Test_Validation_DateHour";
        String firstLine2 = "Test_Code;NHS_Code;Lab_ID;CitizenCard_Number;NHS_Number;TIN;BirthDay;PhoneNumber;Name;Email ;Address;TestType;Category;HB000;WBC00;PLT00;RBC00;Category;HDL00;Category;IgGAN;Test_Reg_DateHour;Test_Chemical_DateHour;Test_Doctor_DateHour;Test_Validation_DateHour";
        String firstLine3 = "Test_Code;NHS_Code;Lab_ID;CitizenCard_Number;NHS_Number;TIN;BirthDay;PhoneNumber;Name;E/mail ;Address;TestType;Category;HB000;WBC00;PLT00;RBC00;Category;HDL00;Category;IgGAN;Test_Reg_DateHour;Test_Chemical_DateHour;Test_Doctor_DateHour;Test_Validation_DateHour";

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {


            String line = br.readLine();


            if (line.equals(firstLine1) || line.equals(firstLine2) || line.equals(firstLine3)) {


                while (line != null) {

                    boolean a = true;
                    String[] metadata = line.split(";");


                    line = br.readLine();


                    try {
                        createClientfromFile(metadata);
                    } catch (Exception e) {
                        a = false;
                        errorcount++;
                        continue;
                    }
                    try {
                        verifyClinicalAnalysisLab(metadata);
                    } catch (Exception e) {
                        a = false;
                        errorcount++;
                        continue;
                    }
                    try {
                        createTestfromFile(metadata);

                    } catch (Exception e) {
                        a = false;
                        errorcount++;
                        continue;
                    }

                    testFileList.add(Arrays.toString(metadata));


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method gets a list of the tests that the CSV file contains, counts and shows the errors found on it
     * @return a list that has the tests plus the number of errors of it
     */
    public List<String> getTestFileList() {
        String error = (errorcount - 1) + " errors found.";
        testFileList.add(error);
        return testFileList;
    }
    /**
     * This method creates the client with the information presented on the CSV file
     * @param metadata array string that represents the saved lines from the CSV file
     * @return a boolean value representing the success of the operation
     * @throws ParseException signals that an error has been reached unexpectedly while parsing
     */
    public boolean createClientfromFile(String[] metadata) throws ParseException {


        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(metadata[6]);

        cstore.createClient(metadata[7], metadata[3], metadata[4], metadata[5], date, ' ', metadata[9], metadata[8]);
        cstore.saveClient();
        String pwd = PasswordGenerator.getPassword();
        boolean success = true;
        try {
            App.getInstance().getCompany().getAuthFacade().addUserWithRole(metadata[8], metadata[9], pwd, Constants.ROLE_CLIENT);
        } catch (Exception e) {
            success = false;
        }
        if (success) {
            Email.sendPasswordNotification(metadata[8], metadata[9], pwd);

        }

        return success;
    }
    /**
     * This method verifies if the Clinical Lab ID already exists
     * @param metadata array string that represents the saved lines from the CSV file
     * @return a boolean value that if it's true then it means the clinical laboratory already exists
     */
    public boolean verifyClinicalAnalysisLab(String[] metadata) {

        return store.verifyClinicalLabID(metadata[2]);
    }
    /**
     * This method creates a test from the CSV file
     * @param metadata array string that represents the saved lines from the CSV file
     * @return the test with all the necessary data and changes its state to validated
     * @throws ParseException signals that an error has been reached unexpectedly while parsing
     */
    public boolean createTestfromFile(String[] metadata) {
        TestType testtype = ttstore.getTestTypeExist(metadata[11]);


        List<ParameterCategory> pcList = new ArrayList<>();

        if (!metadata[12].equalsIgnoreCase("NA")) {

            ParameterCategory pc1 = pcstore.getParameterCategoryExist(metadata[12]);
            if (pc1 == null) {
                pc1 = pcstore.createParameterCategory("12345", metadata[12]);
            }
            pcList.add(pc1);
        }
        if (!metadata[17].equalsIgnoreCase("NA")) {
            ParameterCategory pc2 = pcstore.getParameterCategoryExist(metadata[17]);
            if (pc2 == null) {
                pc2 = pcstore.createParameterCategory("12346", metadata[12]);
            }
            pcList.add(pc2);
        }
        if (!metadata[19].equalsIgnoreCase("NA")) {
            ParameterCategory pc3 = pcstore.getParameterCategoryExist(metadata[19]);
            if (pc3 == null) {
                pc3 = pcstore.createParameterCategory("12347", metadata[12]);
            }
            pcList.add(pc3);
        }

        List<Parameter> pList = new ArrayList<>();

        if (!(metadata[13].equalsIgnoreCase("NA"))) {
            Parameter p1 = pstore.getParameterExist("HB");
            pList.add(p1);
        }
        if (!(metadata[14].equalsIgnoreCase("NA"))) {
            Parameter p2 = pstore.getParameterExist("WBC");
            pList.add(p2);
        }
        if (!(metadata[15].equalsIgnoreCase("NA"))) {
            Parameter p3 = pstore.getParameterExist("PLT");
            pList.add(p3);
        }
        if (!(metadata[16].equalsIgnoreCase("NA"))) {
            Parameter p4 = pstore.getParameterExist("RBC");
            pList.add(p4);
        }
        if (!(metadata[20].equalsIgnoreCase("NA"))) {
            Parameter p5 = pstore.getParameterExist("COVID");
            pList.add(p5);
        }


        Test t = tstore.createTest(metadata[4], metadata[5], testtype, pcList, pList);
        tstore.saveTest();
        t.createReport("Undefined");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        t.changeState(Test.State.SAMPLE_COLLECTED);


        if (!(metadata[13].equalsIgnoreCase("NA"))) {
            t.addTestResult("HB000", Double.parseDouble(metadata[13].replace(",", ".")));

        }
        if (!(metadata[14].equalsIgnoreCase("NA"))) {
            t.addTestResult("WBC00", Double.parseDouble(metadata[14].replace(",", ".")));

        }
        if (!(metadata[15].equalsIgnoreCase("NA"))) {
            t.addTestResult("PLT00", Double.parseDouble(metadata[15].replace(",", ".")));

        }
        if (!(metadata[16].equalsIgnoreCase("NA"))) {
            t.addTestResult("RBC00", Double.parseDouble(metadata[16].replace(",", ".")));

        }
        if (!(metadata[20].equalsIgnoreCase("NA"))) {
            t.addTestResult("IgGAN", Double.parseDouble(metadata[20].replace(",", ".")));

        }

        String date1 = metadata[21];
        String date2 = metadata[22];
        String date3 = metadata[23];
        String date4 = metadata[24];


        String[] DateSplitted1 = date1.split(" ");
        String[] DateSplitted2 = date2.split(" ");
        String[] DateSplitted3 = date3.split(" ");
        String[] DateSplitted4 = date4.split(" ");

        if (DateSplitted1[1].length() == 4) {
            date1 = DateSplitted1[0] + " 0" + DateSplitted1[1];
        }
        if (DateSplitted2[1].length() == 4) {
            date2 = DateSplitted2[0] + " 0" + DateSplitted2[1];
        }
        if (DateSplitted3[1].length() == 4) {
            date3 = DateSplitted3[0] + " 0" + DateSplitted3[1];
        }
        if (DateSplitted4[1].length() == 4) {
            date4 = DateSplitted4[0] + " 0" + DateSplitted4[1];
        }


        LocalDateTime date11 = LocalDateTime.parse(date1, formatter);
        t.setCreatedDate(date11);
        LocalDateTime date22 = LocalDateTime.parse(date2, formatter);
        t.setAnalysedData(date22);
        LocalDateTime date33 = LocalDateTime.parse(date3, formatter);
        t.setDiagnosticDate(date33);
        LocalDateTime date44 = LocalDateTime.parse(date4, formatter);
        t.setValidatedDate(date44);


        t.changeState(Test.State.VALIDATED);

        return true;
    }

    public int getErrorcount() {
        return errorcount;
    }
}