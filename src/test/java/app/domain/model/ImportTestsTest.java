package app.domain.model;



import org.junit.Assert;
import org.junit.Test;


public class ImportTestsTest {

    @Test
    public void CreateValidCategoryTest() {
        //Arrange + Act
        String fileName = "csv/tests_BloodMDISCCSV.csv";

        ImportTests aa = new ImportTests();
        aa.readTestFromCSV(fileName);
        Assert.assertNotNull(aa.getTestFileList());

    }

}