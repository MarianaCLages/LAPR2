package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class TestParameterResultTest {
    @Test
    public void TestParameterResultTest() {
        ParameterCategory cat = new ParameterCategory("HB000", "name");
        Parameter pa = new Parameter("HB000", "Haemoglo", "description", cat);
        RefValue ref = new RefValue(0.12, 0.8, "me");
        TestParameterResult result = new TestParameterResult(0.854, pa.getCode(), ref);

        Assert.assertNotNull(result);
    }
    @Test
    public void getResultTest() {
        ParameterCategory cat = new ParameterCategory("HB000", "name");
        Parameter pa = new Parameter("HB000", "Haemoglo", "description", cat);
        RefValue ref = new RefValue(0.12, 0.8, "me");
        TestParameterResult result = new TestParameterResult(0.854, pa.getCode(), ref);


        Assert.assertNotNull(result);
    }



}