package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {
    Role role = new Role("1", "Medical Lab Technician" );

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeNameWithNum() {
        //Arrange + Act
        Employee employee = new Employee("Bino1","AtuaTerra", "912345678","something@isep.com","11111111111111111",role  );
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeePhoneNumberWithLetter() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "91234567A","something@isep.com","11111111111111111",role  );
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeSOCWithLetter() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "912345678","something@isep.com","11111111111111111A",role  );
    }

}