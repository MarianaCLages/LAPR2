package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class RefValueAdapter1Test {

    @Test
    public void getRefValueNotNull() {
        RefValueAdapter1 ref = new RefValueAdapter1();
        TestParameter tp = new TestParameter("IgGAN");

        RefValue refValue = ref.getRefValue(tp.getpCode());
        Assert.assertNotNull(refValue);
    }

    @Test
    public void getRefValueMaxRight() {
        RefValueAdapter1 ref = new RefValueAdapter1();
        TestParameter tp = new TestParameter("IgGAN");

        RefValue refValue = ref.getRefValue(tp.getpCode());

        double actual = refValue.getMaxValue();
        double expected = 1.4D;

        Assert.assertEquals(actual, expected, 0);
    }

    @Test
    public void getRefValueMinRight() {
        RefValueAdapter1 ref = new RefValueAdapter1();
        TestParameter tp = new TestParameter("IgGAN");

        RefValue refValue = ref.getRefValue(tp.getpCode());

        double actual = refValue.getMinValue();
        double expected = 0.0D;

        Assert.assertEquals(actual, expected, 0);
    }


    @Test
    public void getRefValueMetricRight() {
        RefValueAdapter1 ref = new RefValueAdapter1();
        TestParameter tp = new TestParameter("IgGAN");

        RefValue refValue = ref.getRefValue(tp.getpCode());

        String actual = refValue.getMetric();
        String expected = "Index (S/C) Value";

        Assert.assertEquals(actual, expected);
    }

}