package app.domain.shared;

import org.junit.Assert;
import org.junit.Test;

public class LinearRegressionTest {
    double delta = 0.000001;
    double[] m1 = {27, 58, 86, 120, 140, 152, 169, 218, 226, 258};
    double[] m2 = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

    LinearRegression l = new LinearRegression(m1, m2);

    @Test(expected = IllegalArgumentException.class)
    public void LinearRegressionTest() {
        double[] m1 = {27, 58, 86, 120, 140, 152, 169, 218, 226, 258};
        double[] m2 = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 45};

        LinearRegression l = new LinearRegression(m1, m2);
    }

    @Test
    public void intercept() {
        Assert.assertEquals(-1.5906251473292201, l.intercept(), delta);
    }

    @Test
    public void slope() {
        Assert.assertEquals(0.20007307529112256, l.slope(), delta);
    }

    @Test
    public void r2() {
        Assert.assertEquals(0.9879972227103436, l.getR() * l.getR(), delta);
    }

    @Test
    public void predict() {
        Assert.assertEquals(1.4104709820376184, l.predict(15), delta);
    }

    @Test
    public void getCriticValueStudent() {
        Assert.assertEquals(2.228138851986303, l.getCriticValueStudent(0.025), delta);
    }

    @Test
    public void getCriticValueFisher() {
        Assert.assertEquals(2.228138851986303, l.getCriticValueStudent(0.025), delta);

    }

    @Test
    public void lowerLimitAnswer() {
        Assert.assertEquals(34.391315333331846, l.lowerLimitAnswer(200, 0.025), delta);
    }

    @Test
    public void upperLimitAnswer() {
        Assert.assertEquals(42.45666448845874, l.upperLimitAnswer(200, 0.025), delta);

    }

    @Test
    public void upperLimitParama() {
        Assert.assertEquals(0.9352584008106137, l.upperLimitParama(0.025), delta);
    }

    @Test
    public void lowerLimitParama() {
        Assert.assertEquals(-4.116508695469054, l.lowerLimitParama(0.025), delta);

    }

    @Test
    public void upperLimitParamb() {
        Assert.assertEquals(0.21744503917103888, l.upperLimitParamb(0.025), delta);
    }

    @Test
    public void lowerLimitParamb() {
        Assert.assertEquals(0.18270111141120624, l.lowerLimitParamb(0.025), delta);

    }

    @Test
    public void getSt() {
        Assert.assertEquals(2062.500000000001, l.getSt(), delta);
    }

    @Test
    public void getMse() {
        Assert.assertEquals(3.0944660199896297, l.getMse(), delta);

    }

    @Test
    public void getMsr() {
        Assert.assertEquals(2037.7442718400837, l.getMsr(), delta);

    }

    @Test
    public void getF0() {
        Assert.assertEquals(658.5124084984823, l.getF0(), delta);

    }

    @Test
    public void getSr() {
        Assert.assertEquals(2037.7442718400837, l.getSr(), delta);
    }

    @Test
    public void getSe() {
        Assert.assertEquals(24.755728159917037, l.getSe(), delta);

    }

    @Test
    public void getR() {
        Assert.assertEquals(0.9939804941297106, l.getR(), delta);

    }


}