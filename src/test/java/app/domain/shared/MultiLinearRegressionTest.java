package app.domain.shared;

import app.domain.shared.exceptions.InvalidLengthException;
import org.junit.Assert;
import org.junit.Test;

public class MultiLinearRegressionTest {
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
    public void getEstimate() throws InvalidLengthException {
        double[] x = {170, 12};
        Assert.assertEquals(23.981069387755163, s.getEstimate(x), 0.00000001);
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
        Assert.assertEquals(s.getSlope1(), 0.11053775510204078, 0.000001);
    }

    @Test
    public void x2Test() {
        Assert.assertEquals(0.686277551020409, s.getSlope2(), 0.000001);
    }

    @Test
    public void x0Test() {
        Assert.assertEquals(-3.0456795918366772, s.getIntercept(), 0.000001);
    }

    @Test
    public void SQtTest() {
        Assert.assertEquals(148.75500000000102, s.getSQt(), 0.000001);
    }

    @Test
    public void SQrTest() {
        Assert.assertEquals(144.2431340816438, s.getSQr(), 0.000001);
    }

    @Test
    public void SQeTest() {
        Assert.assertEquals(4.511865918357216, s.getSQe(), 0.000001);
    }

    @Test
    public void r2Test() {
        Assert.assertEquals(0.9696691478043953, s.getR2(), 0.000001);
    }

    @Test
    public void r2ajustedTest() {
        Assert.assertEquals(0.9494485796739921, s.getR2Ajusted(), 0.000001);
    }

    @Test
    public void MQeTest() {
        Assert.assertEquals(1.503955306119072, s.getMQe(), 0.000001);
    }

    @Test
    public void MQrTest() {
        Assert.assertEquals(72.1215670408219, s.getMQr(), 0.000001);
    }
}