package app.domain.mappers.dto;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTypeDTOTest {

    @Test
    public void toStringTest() {
        TestTypeDTO s = new TestTypeDTO("BL000","something");
        String a = s.toString();
        String b = "TestType :testID=BL000, description=something";
        Assert.assertEquals(a,b);
    }
}

