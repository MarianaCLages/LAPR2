package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleTest {

    @Test
    public void getRoleIdRight(){
        Role role = new Role("0","Clinical Chemistry Technologist");
        String expected = "0";
        String actual = role.getRoleID();

        Assert.assertEquals(actual,expected );
    }

}