package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {
    Role role = new Role("1", "Medical Lab Technician" );

    @Test
    public void RegisterEmployee() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "912345678","something@isep.com","111111111111111111",role);
    }


    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeNameWithNum() {
        //Arrange + Act
        Employee employee = new Employee("Bino1","AtuaTerra", "912345678","something@isep.com","11111111111111111",role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeeNameBlank() {
        //Arrange + Act
        Employee employee = new Employee("","AtuaTerra", "912345678","something@isep.com","11111111111111111",role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeeAddressBlank() {
        //Arrange + Act
        Employee employee = new Employee("Bino","", "912345678","something@isep.com","11111111111111111",role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeePhoneNumberWithLetter() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "91234567A","something@isep.com","11111111111111111",role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeePhoneNumberBlank() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "","something@isep.com","11111111111111111",role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeeEmailBlank() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "912345678","","11111111111111111",role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeeEmailWrongFormat() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "912345678","bino@fusivel","11111111111111111",role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateEmployeeSOCBlank() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "912345678","","",role);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RegisterEmployeeSOCWithLetter() {
        //Arrange + Act
        Employee employee = new Employee("Bino","AtuaTerra", "912345678","something@isep.com","11111111111111111A",role);
    }

}