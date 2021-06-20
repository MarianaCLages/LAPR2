package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class RoleTest {

    @Test
    public void getRoleIdRight() {
        Role role = new Role("0", "Clinical Chemistry Technologist");
        String expected = "0";
        String actual = role.getRoleID();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getRolenameRight() {
        Role role = new Role("0", "Clinical Chemistry Technologist");
        String expected = "Clinical Chemistry Technologist";
        String actual = role.toString();

        Assert.assertEquals(actual, expected);
    }


}