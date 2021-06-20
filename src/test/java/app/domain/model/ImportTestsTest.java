package app.domain.model;



import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.nio.charset.CoderMalfunctionError;
import java.nio.charset.CoderResult;
import java.nio.charset.MalformedInputException;


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


}