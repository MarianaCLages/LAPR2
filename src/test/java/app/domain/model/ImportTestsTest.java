package app.domain.model;



import org.junit.Assert;
import org.junit.Test;


public class ImportTestsTest {

    @Test
    public void readTestFromCSVTest() {
        //Arrange + Act
        String fileName = "csv/tests_BloodMDISCCSV.csv";

        ImportTests aa = new ImportTests();
        aa.readTestFromCSV(fileName);
        Assert.assertNotNull(aa.getTestFileList());

    }
    @Test
    public void readTestFromCSVTest1() {
        //Arrange + Act
        String fileName = "csv/tests_BloodCovidMATCPMDISCCSV.csv";

        ImportTests aa = new ImportTests();
        aa.readTestFromCSV(fileName);
        Assert.assertNotNull(aa.getTestFileList());

    }
    @Test
    public void readTestFromCSVTest2() {
        //Arrange + Act
        String fileName = "csv/tests_CovidMATCPCSV.csv";

        ImportTests aa = new ImportTests();
        aa.readTestFromCSV(fileName);
        Assert.assertNotNull(aa.getTestFileList());

    }
    @Test
    public void readTestFromBlankCSVTest3() {
        //Arrange + Act
        String fileName = "csv/FileInvalid.csv";

        ImportTests aa = new ImportTests();
        aa.readTestFromCSV(fileName);
        Assert.assertNotNull(aa.getTestFileList());

    }

}