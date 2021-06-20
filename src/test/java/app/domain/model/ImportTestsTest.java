package app.domain.model;



import app.controller.CreateTestController;
import app.domain.stores.TestStore;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;


public class ImportTestsTest {

    @Test
    public void readTestFromCSVTest1() {
        //Arrange + Act
        String fileName = "csv/Tests.csv";

        ImportTests aa = new ImportTests();
        aa.readTestFromCSV(fileName);
        Assert.assertNotNull(aa.getTestFileList());

    }


    @Test
    public void readTestFromCSVTest2() {
        //Arrange + Act
        String fileName = "csv/Tests1.csv";

        ImportTests aa = new ImportTests();
        aa.readTestFromCSV(fileName);
        Assert.assertNotNull(aa.getTestFileList());

    }
    @Test
    public void readTestFromCSVTest3() {
        //Arrange + Act
        String fileName = "csv/Tests2.csv";

        ImportTests aa = new ImportTests();
        aa.readTestFromCSV(fileName);
        Assert.assertNotNull(aa.getTestFileList());

    }
    @Test
    public void verifyClinicalAnalysisLabTest() {
        //Arrange + Act
        ImportTests aa = new ImportTests();

        String fileName = "csv/Tests.csv";
        aa.readTestFromCSV(fileName);
        String[] test = {"a", "b", "c"};
        Assert.assertFalse(aa.verifyClinicalAnalysisLab(test));



    }
    @Test
    public void ErrorCountTest() {
        //Arrange + Act
        ImportTests aa = new ImportTests();

        String fileName = "csv/Tests.csv";
        aa.readTestFromCSV(fileName);
        int actual= aa.getErrorcount();
        int expected = 1;
        Assert.assertEquals(actual,expected);
    }
    @Test(expected = NullPointerException.class)
    public void BlankFileTest() {
        //Arrange + Act
        ImportTests aa = new ImportTests();

        String fileName = "csv/Tests3.csv";
        aa.readTestFromCSV(fileName);

    }
    @Test
    public void createClientFileTest() throws ParseException {
        //Arrange + Act
        ImportTests aa = new ImportTests();

        String fileName = "csv/Tests.csv";
        aa.readTestFromCSV(fileName);
        String a = "000000000001;OUQ000003201;001LR;0000000000003201;6000003201;2100003201;05/12/1946;91200003201;Piper GROVES;PiperGROVES2155@gmail.com;128 Street of England 14 Leicester LR 23 OP;Blood;Hemogram;132,4;1;385,2;13,4;Cholesterol;323,8;NA;NA;17/05/2021 8:00;17/05/2021 10:07;18/05/2021 10:32;18/05/2021 11:47";
        String[] b = a.split(";");


        Assert.assertTrue(aa.createClientfromFile(b));



    }
    @Test(expected = IllegalArgumentException.class)
    public void createInvalidClientFileTest() throws ParseException {
        //Arrange + Act
        ImportTests aa = new ImportTests();

        String a = "000000000001;OUQ000003201;001LR;0000000000003201;6000003201;2100003201;05/12/1946;9120A003201;Piper GROVES;PiperGROVES2155@gmail.com;128 Street of England 14 Leicester LR 23 OP;Blood;Hemogram;132,4;1;385,2;13,4;Cholesterol;323,8;NA;NA;17/05/2021 8:00;17/05/2021 10:07;18/05/2021 10:32;18/05/2021 11:47";
        String[] b = a.split(";");


        Assert.assertFalse(aa.createClientfromFile(b));



    }
    @Test
    public void parameterCategorycheckTest() throws ParseException {
        //Arrange + Act
        ImportTests aa = new ImportTests();

        String a = "000000000001;OUQ000003201;001LR;0000000000003201;6000003201;2100003201;05/12/1946;9120A003201;Piper GROVES;PiperGROVES2155@gmail.com;128 Street of England 14 Leicester LR 23 OP;Blood;NA;132,4;1;385,2;13,4;NA;323,8;NA;NA;17/05/2021 8:00;17/05/2021 10:07;18/05/2021 10:32;18/05/2021 11:47";
        String[] b = a.split(";");


        Assert.assertEquals(aa.parameterCategorycheck(b).toString(),"[]");
    }
    @Test
    public void parametercheckTest() throws ParseException {
        //Arrange + Act
        ImportTests aa = new ImportTests();

        String a = "000000000001;OUQ000003201;001LR;0000000000003201;6000003201;2100003201;05/12/1946;9120A003201;Piper GROVES;PiperGROVES2155@gmail.com;128 Street of England 14 Leicester LR 23 OP;NA;NA;NA;NA;NA;NA;NA;NA;NA;NA;17/05/2021 8:00;17/05/2021 10:07;18/05/2021 10:32;18/05/2021 11:47";
        String[] b = a.split(";");


        Assert.assertEquals(aa.parametercheck(b).toString(),"[]");



    }








}