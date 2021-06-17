package app.domain.model;

import app.controller.App;
import app.domain.stores.*;
import auth.AuthFacade;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/** * Simple Java program to read CSV file in Java. In this program we will read * list of books stored in CSV file as comma separated values. * * @author WINDOWS 8 * */
public class ImportTests {

    int errorcount = 0;
    TestTypeStore ttstore;
    ClientStore cstore;
    AuthFacade authFacade;
    ClinicalAnalysisLabStore store;
    ParameterCategoryStore pcstore;
    ParameterStore pstore;
    Company company;
    TestStore tstore;
    List<String> testFileList = new ArrayList<>();

    public ImportTests() {
        App app = App.getInstance();
        Company company = app.getCompany();
        authFacade = company.getAuthFacade();
        cstore = company.getClientList();
        pcstore = company.getParameterCategoryList();
        tstore = company.getTestList();
        ttstore = company.getTestTypeList();
        pstore = company.getParameterList();
        store = company.getClinicalAnalysisLabList();

    }


    public void readTestFromCSV(String filepath) {



        Path path = Paths.get(filepath);
        String firstLine = "Test_Code;NHS_Code;Lab_ID;CitizenCard_Number;NHS_Number;TIN;BirthDay;PhoneNumber;Name;E-mail ;Address;TestType;Category;HB000;WBC00;PLT00;RBC00;Category;HDL00;Category;IgGAN;Test_Reg_DateHour;Test_Chemical_DateHour;Test_Doctor_DateHour;Test_Validation_DateHour";
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {



            String line = br.readLine();


            if(line.equals(firstLine)){


                while (line != null) {


                    String[] metadata = line.split(";");


                    line = br.readLine();


                    try {
                        createClientfromFile(metadata);
                    } catch (Exception e) {
                        errorcount++;
                        continue;
                    }
                    try {
                        verifyClinic(metadata);
                    } catch (Exception e) {
                        errorcount++;
                        continue;
                    }
                    try {
                        createTestfromFile(metadata);

                    } catch (Exception e) {
                        errorcount++;
                        continue;
                    }

                    if(createClientfromFile(metadata) && verifyClinic(metadata) && createTestfromFile(metadata)){
                        testFileList.add(Arrays.toString(metadata));
                        /*System.out.println(Arrays.toString(metadata));*/
                    }

                }
            }
        } catch (IOException | ParseException ioe) {
            ioe.printStackTrace();
        }
    }

    public List<String> getTestFileList() {
        String error = errorcount + " errors found.";
        testFileList.add(error);
        return testFileList;
    }

    public boolean createClientfromFile(String[] metadata) throws ParseException {


        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(metadata[6]);

        cstore.createClient(metadata[7],metadata[3],metadata[4],metadata[5], date,' ',metadata[9],metadata[8]);
        boolean save;
        save = cstore.saveClient();
        /*cstore.addUser(company);*/

        return save;
    }

    public boolean verifyClinic(String[] metadata) {

        return store.verifyClinicalLabID(metadata[2]);
    }

    public boolean createTestfromFile(String[] metadata) throws ParseException {
        TestType testtype = ttstore.getTestTypeExist(metadata[11]);


        List<ParameterCategory> pcList = new ArrayList<>();

        if (!metadata[12].equalsIgnoreCase("NA")){

            ParameterCategory pc1 = pcstore.getParameterCategoryExist(metadata[12]);
            if(pc1==null){
                pc1 = pcstore.createParameterCategory("12345",metadata[12]);
            }
            pcList.add(pc1);
        }
        if (!metadata[17].equalsIgnoreCase("NA")){
            ParameterCategory pc2 = pcstore.getParameterCategoryExist(metadata[17]);
            if(pc2==null){
                pc2 = pcstore.createParameterCategory("12346",metadata[12]);
            }
            pcList.add(pc2);
        }
        if (!metadata[19].equalsIgnoreCase("NA")){
            ParameterCategory pc3 = pcstore.getParameterCategoryExist(metadata[19]);
            if(pc3==null){
                pc3 = pcstore.createParameterCategory("12347",metadata[12]);
            }
            pcList.add(pc3);
        }

        List<Parameter> pList = new ArrayList<>();

        if (!(metadata[13].equalsIgnoreCase("NA"))){
            Parameter p1 = pstore.getParameterExist("HB");
            pList.add(p1);
        }
        if (!(metadata[14].equalsIgnoreCase("NA"))){
            Parameter p2 = pstore.getParameterExist("WBC");
            pList.add(p2);
        }
        if (!(metadata[15].equalsIgnoreCase("NA"))){
            Parameter p3 = pstore.getParameterExist("PLT");
            pList.add(p3);
        }
        if (!(metadata[16].equalsIgnoreCase("NA"))){
            Parameter p4 = pstore.getParameterExist("RBC");
            pList.add(p4);
        }
        if (!(metadata[20].equalsIgnoreCase("NA"))){
            Parameter p5 = pstore.getParameterExist("COVID");
            pList.add(p5);
        }


        Test t = tstore.createTest(metadata[4],metadata[5],testtype,pcList,pList);
        tstore.saveTest();




        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        t.changeState("SAMPLE_COLLECTED");

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
        t.setSampleCreatedDate(date11);
        LocalDateTime date22 = LocalDateTime.parse(date2, formatter);
        t.setAnalysedData(date22);
        LocalDateTime date33 = LocalDateTime.parse(date3, formatter);
        t.setDiagnosticDate(date33);
        LocalDateTime date44 = LocalDateTime.parse(date4, formatter);
        t.setValidatedDate(date44);




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
        t.changeState("VALIDATED");

        return true;
    }
}







