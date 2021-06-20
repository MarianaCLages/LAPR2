package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestParameterTest {

    @Test
    public void testToString() {
        TestParameter testParameter = new TestParameter("4568");
        Assert.assertEquals(testParameter.toString(),"TestParameter{pCode='4568', testParameterResult=null}");
    }
}