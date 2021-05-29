package app.domain.mappers.dto;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDTOTest {

    @Test
    public void toStringTest() {
        TestDTO s = new TestDTO("123456789012","12345");
        String a = s.toString();
        String b = "Test: testNhsNumber= 123456789012, testCode= 12345";
        Assert.assertEquals(a,b);
    }
}