package app.domain.model;

import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.exceptions.InvalidLengthException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringBuilderReportTest {

    double[] x = {1, 2};
    double[] y = {3, 4};

    double[][] xOBS = {{1, 2}, {3, 4}};
    double[] yOBS = {10, 2};

    LinearRegression linearRegression = new LinearRegression(x, y);
    MultiLinearRegression multiLinearRegression = new MultiLinearRegression(xOBS, y);

    StringBuilderReport stringBuilderReport = new StringBuilderReport(linearRegression);
    StringBuilderReport stringBuilderReport2 = new StringBuilderReport(multiLinearRegression);


    @Test
    public void clear() {
            stringBuilderReport.clear();

    }

    @Test
    public void setConfidenceValues() {
            stringBuilderReport.setConfidenceValues(10, 10, 10);

    }

    @Test
    public void setvalues() {

            stringBuilderReport.setvalues(x, y, 10);
            stringBuilderReport.setvalues(xOBS, y, 10);
<<<<<<< HEAD
        } catch (Exception e) {

        }
=======
>>>>>>> 4196bcc6c67d09a4351bac6a9b097fd839ec5bb3
    }

    @Test(expected = NotStrictlyPositiveException.class)
    public void stringConstructionMultiLinearRegression() throws InvalidLengthException {

            stringBuilderReport2.setvalues(xOBS, yOBS, 20);
            stringBuilderReport2.setConfidenceValues(0.3, 0.3, 0.3);
            stringBuilderReport2.stringConstructionMultiLinearRegression();
<<<<<<< HEAD
        } catch (Exception e) {

        }
=======
>>>>>>> 4196bcc6c67d09a4351bac6a9b097fd839ec5bb3
    }

    @Test (expected = NotStrictlyPositiveException.class)
    public void stringConstructionLinearRegression()  {
            stringBuilderReport.setvalues(x, yOBS, 20);
            stringBuilderReport.setConfidenceValues(0.3, 0.3, 0.3);
            stringBuilderReport.stringConstructionLinearRegression();
<<<<<<< HEAD
        } catch (Exception e) {
=======
>>>>>>> 4196bcc6c67d09a4351bac6a9b097fd839ec5bb3

    }

    @Test
    public void printCovidTestsPerInterval() {
            stringBuilderReport.printCovidTestsPerInterval("Day");
            stringBuilderReport.printCovidTestsPerInterval("Week");
            stringBuilderReport.printCovidTestsPerInterval("Month");
<<<<<<< HEAD
        } catch (Exception e) {
=======
>>>>>>> 4196bcc6c67d09a4351bac6a9b097fd839ec5bb3

    }

    @Test
    public void printTheCovidTestsIntoTheNHSReportDay() {
    }

    @Test
    public void printTheCovidTestsIntoTheNHSReportWeek() {
    }

    @Test
    public void printTheCovidTestsIntoTheNHSReportMonthly() {
    }

    @Test
    public void getSb() {
        Assert.assertNotNull(stringBuilderReport.getSb());
    }
}