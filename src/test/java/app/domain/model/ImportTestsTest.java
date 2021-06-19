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
        String fileName = "csv/tests_BloodCovidMATCPMDISCCSV.csv";

        ImportTests aa = new ImportTests();
        aa.readTestFromCSV(fileName);
        Assert.assertNotNull(aa.getTestFileList());

    }

}