package app.domain.model;

import app.domain.model.Role;
import app.domain.model.SpecialistDoctor;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpecialistDoctorTest {
    Role role = new Role("4","SpecialistDoctor" );

    @Test
    public void checkDoctorIndexNumber() {
        //Arrange + Act
        SpecialistDoctor employee = new SpecialistDoctor("B00001","Bino","AtuaTerra", "91234567811","something@isep.com","111111111111111111","1111111111111111",role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterSpecialistDoctorIndexNumberLetter() {
        //Arrange + Act
        SpecialistDoctor employee = new SpecialistDoctor("B00001","Bino","AtuaTerra", "91234567811","something@isep.com","111111111111111111","1111111a111111111",role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterSpecialistDoctorIndexNumberDot() {
        //Arrange + Act
        SpecialistDoctor employee = new SpecialistDoctor("B00001","Bino","AtuaTerra", "91234567811","something@isep.com","11111111111111111. ","1111111a111111111",role);
    }

    @Test
    public void toStringTest(){
        SpecialistDoctor employee = new SpecialistDoctor("B00001","Bino","AtuaTerra", "91234567811","something@isep.com","111111111111111111","1111111111111111",role);
        String expected = "Employee: ID=B00001, name=Bino, address=AtuaTerra, phonenumber=91234567811, email=something@isep.com, soc=111111111111111111, Role="+ role.toString()+ ", DoctorIndexNumber=1111111111111111";
        String actual = employee.toString();
        Assert.assertEquals(expected,actual);
    }


}