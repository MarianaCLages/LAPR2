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


}