package app.domain.shared;

import app.domain.shared.exceptions.InvalidLengthException;
import org.junit.Assert;
import org.junit.Test;

public class MultiLinearRegressionTest {
    double delta = 0.0001;
    double[][] matrix1 = {
            {120, 19},
            {200, 8},
            {150, 12},
            {180, 15},
            {240, 16},
            {250, 13}
    };
    double[] matrixb = {23.8, 24.2, 22.0, 26.2, 33.5, 35};

    MultiLinearRegression s = new MultiLinearRegression(matrix1, matrixb);

   @Test
    public void getLowerLimitCoefficient(){
        Assert.assertEquals(0.08365938736194439,s.lowerLimitCoeficient(1,0.05),delta);
    }

    @Test
    public void getUpperLimitCoefficient(){
        Assert.assertEquals(0.13741612284213717,s.upperLimitCoeficient(1,0.05),delta);
    }

    @Test
    public void getEstimate() throws InvalidLengthException {
        double[] x = {170, 12};
        Assert.assertEquals(23.981069387755163, s.getEstimate(x), delta);
    }

    @Test
    public void getLowerLimitEstimate() throws InvalidLengthException {
        double[] x = {170, 12};
        Assert.assertEquals(20.714607676358867, s.lowerLimitAnswer(x,0.05), delta);
    }

    @Test
    public void getUpperLimitEstimate() throws InvalidLengthException {
        double[] x = {170, 12};
        Assert.assertEquals(27.24753109915146, s.upperLimitAnswer(x,0.05), delta);
    }

    @Test(expected = InvalidLengthException.class)
    public void getEstimateError() throws InvalidLengthException {
        double[] x = {170, 12, 5};
        s.getEstimate(x);
    }

    @Test(expected = IllegalArgumentException.class)
    public void differentLengthsTest() {
        double[][] matrix1 = {
                {120, 19},
                {200, 8},
                {150, 12},
                {180, 15},
                {240, 16},
                {250, 13}
        };
        double[] matrixb = {23.8, 24.2, 22.0, 26.2, 33.5, 35, 5};

        MultiLinearRegression s = new MultiLinearRegression(matrix1, matrixb);

    }

    @Test
    public void x1Test() {
        Assert.assertEquals(s.getSlope1(), 0.11053775510204078, delta);
    }

    @Test
    public void x2Test() {
        Assert.assertEquals(0.686277551020409, s.getSlope2(), delta);
    }

    @Test
    public void x0Test() {
        Assert.assertEquals(-3.0456795918366772, s.getIntercept(), delta);
    }

    @Test
    public void SQtTest() {
        Assert.assertEquals(148.75500000000102, s.getSQt(), delta);
    }

    @Test
    public void SQrTest() {
        Assert.assertEquals(144.2431340816438, s.getSQr(), delta);
    }

    @Test
    public void SQeTest() {
        Assert.assertEquals(4.511865918357216, s.getSQe(), delta);
    }

    @Test
    public void r2Test() {
        Assert.assertEquals(0.9696691478043953, s.getR2(), delta);
    }

    @Test
    public void r2ajustedTest() {
        Assert.assertEquals(0.9494485796739921, s.getR2Ajusted(), delta);
    }

    @Test
    public void MQeTest() {
        Assert.assertEquals(1.503955306119072, s.getMQe(), delta);
    }

    @Test
    public void MQrTest() {
        Assert.assertEquals(72.1215670408219, s.getMQr(), delta);
    }

    @Test
    public void getF0Test(){
        Assert.assertEquals(47.95459462617292,s.getF0(),delta);
    }

   @Test
    public void getF1(){
        Assert.assertEquals(9.552094495921152,s.getCriticValueFisher(0.05),delta);
    }

    @Test
    public void getTestStats(){
        Assert.assertEquals(9.678248081789736,s.getTestStatistics(1),delta);
    }

    @Test
    public void getCriticalValue(){
        Assert.assertEquals(3.1824463048574763,s.getCriticValueStudent(0.025),delta);
    }
}