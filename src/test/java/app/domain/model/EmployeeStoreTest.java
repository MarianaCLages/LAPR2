package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeStoreTest {
    Role role = new Role("1", "Medical Lab Technician" );


    @Test
    public void validateCreateEmployeeID() {
        //Arrange + act
        EmployeeStore store = new EmployeeStore();
        Employee employeeID1 =new Employee("B00001","Bino", "casa", "91111111111","something@isep.pt","111111111111111111",role ) ;

        //Assert
        Assert.assertTrue(store.ValidateEmployee(employeeID1));
    }


    @Test
    public void EmployeeIDcheck() {
        //Arrange + Act
        String expected = "Employee: ID=B00001, name=Bino, address=AtuaTerra, phonenumber=91234567811, email=something@isep.com, SOC=111111111111111111, Role=Medical Lab Technician";
        EmployeeStore store = new EmployeeStore();
        store.CreateEmployee("Bino","AtuaTerra", "91234567811","something@isep.com","111111111111111111","",1);

        Employee employee = new Employee("B00001","Bino","AtuaTerra", "91234567811","something@isep.com","111111111111111111",role);
        String actual = store.getEm().toString();

        Assert.assertEquals(expected,actual);

    }
}