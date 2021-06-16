package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ImportTestsTest {

    @Test
    public void CreateValidCategoryTest() throws IOException {
        //Arrange + Act
        String fileName = "C:\\Users\\EN\\Documents\\prog lapr2 clone2\\csv\\tests_BloodMDISCCSV.csv";

        ImportTests aa = new ImportTests();
        aa.readTestFromCSV(fileName);
        System.out.println((aa.getTestFileList()));

    }

}