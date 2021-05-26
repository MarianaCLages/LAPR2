package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RefValueAdapter3Test {

    @org.junit.Test
    public void getRefValueNotNull(){
        RefValueAdapter3 ref = new RefValueAdapter3();
        TestParameter tp = new TestParameter("HB000");

        RefValue refValue = ref.getRefValue(tp.getpCode());
        Assert.assertNotNull(refValue);
    }

    @org.junit.Test
    public void getRefValueMaxRight(){
        RefValueAdapter3 ref = new RefValueAdapter3();
        TestParameter tp = new TestParameter("HB000");

        RefValue refValue = ref.getRefValue(tp.getpCode());
        double actual = refValue.getMaxValue();
        double expected = 180.0D;

        Assert.assertEquals(actual,expected,0);
    }

    @org.junit.Test
    public void getRefValueMinRight(){
        RefValueAdapter3 ref = new RefValueAdapter3();
        TestParameter tp = new TestParameter("HB000");

        RefValue refValue = ref.getRefValue(tp.getpCode());

        double actual = refValue.getMinValue();
        double expected = 130.0D;

        Assert.assertEquals(actual,expected,0);
    }


    @Test
    public void getRefValueMetricRight(){
        RefValueAdapter3 ref = new RefValueAdapter3();
        TestParameter tp = new TestParameter("HB000");

        RefValue refValue = ref.getRefValue(tp.getpCode());

        String actual = refValue.getMetric();
        String expected = "g/L";

        Assert.assertEquals(actual,expected);

    }

}