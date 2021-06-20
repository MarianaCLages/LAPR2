package app.domain.model;



import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.nio.charset.CoderMalfunctionError;
import java.nio.charset.CoderResult;
import java.nio.charset.MalformedInputException;
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



}