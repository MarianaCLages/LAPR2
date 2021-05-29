package app.domain.mappers.dto;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterDTOTest {

    @Test
    public void toStringTest() {
        ParameterDTO s = new ParameterDTO("Hemogram","12345");
        String a = s.toString();
        String b = "Parameter: name=Hemogram, code=12345";
        Assert.assertEquals(a,b);
    }

}