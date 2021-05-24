package app.domain.model;

import app.domain.stores.EmployeeStore;
import org.junit.Assert;
import org.junit.Test;

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
    public void validateValidEmployee() {
        //Arrange
        EmployeeStore store = new EmployeeStore();
        Employee em1 = new Employee("B00001","Bino", "casa", "91111111111","something@isep.pt","111111111111111111",role);
        Employee em2 = new Employee("B00001","Bino", "casa", "91111111111","something@isep.pt","111111111111111111",role);
        //Act
        store.add(em1);
        //Assert
        Assert.assertFalse(store.ValidateEmployee(em1));
    }
    @Test

    public void validateValidEmployeeAlreadyExists() {
        //Arrange
        EmployeeStore store = new EmployeeStore();
        Employee em1 = new Employee("B00001","Bino", "casa", "91111111111","something@isep.pt","111111111111111111",role);
        //Act
        store.add(em1);
        //Assert
        Assert.assertFalse(store.ValidateEmployee(em1));
    }
    @Test
    public void validateEmployeeIsNull() {
        //Arrange + act
        EmployeeStore store = new EmployeeStore();
        Employee em1 = null;
        //Assert
        Assert.assertFalse(store.ValidateEmployee(em1));
    }
    @Test
    public void getIDRight() {
        //Arrange
        EmployeeStore store = new EmployeeStore();
        Employee em1 = new Employee("B00001","Bino", "casa", "91111111111","something@isep.pt","111111111111111111",role);
        Employee em2 = new Employee("B00002","Bino", "casa do vizinho", "91111111112","something@isep.pc","111111111111111112",role);
        store.add(em1);
        store.add(em2);
        //act
        Employee expected = store.get(1);
        Employee actual = em2;
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = IndexOutOfBoundsException.class )
    public void getIDOutOfLimits() {
        //Arrange
        EmployeeStore store = new EmployeeStore();
        Employee em1 = new Employee("B00001","Bino", "casa", "91111111111","something@isep.pt","111111111111111111",role);
        Employee em2 = new Employee("B00002","Bino", "casa do vizinho", "91111111112","something@isep.pc","111111111111111112",role);
        store.add(em1);
        store.add(em2);
        //act
        Employee expected = store.get(2);
        Employee actual = em2;
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = IndexOutOfBoundsException.class )
    public void getIDNegative() {
        //Arrange
        EmployeeStore store = new EmployeeStore();
        Employee em1 = new Employee("B00001","Bino", "casa", "91111111111","something@isep.pt","111111111111111111",role);
        Employee em2 = new Employee("B00002","Bino", "casa do vizinho", "91111111112","something@isep.pc","111111111111111112",role);
        store.add(em1);
        store.add(em2);
        //act
        Employee expected = store.get(-2);
        Employee actual = em2;
        Assert.assertEquals(expected,actual);
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
    @Test
    public void createValidEmployee() {
        EmployeeStore store = new EmployeeStore();

        Assert.assertNotNull(store.CreateEmployee("Bino", "casa", "91111111111","something@isep.pt","111111111111111111","1111111111111",1));

    }
    @Test
    public void getEmValid() {
        EmployeeStore store = new EmployeeStore();
        Employee em1 = store.CreateEmployee("Bino", "casa", "91111111111","something@isep.pt","111111111111111111","1111111111111",1);
        Employee expected = em1;
        Employee actual= store.getEm();

        Assert.assertEquals(expected,actual);

    }
    @Test
    public void containsTrue() {
        //Assert
        EmployeeStore store = new EmployeeStore();
        Employee em1 = new Employee("B00001","Bino", "casa", "91111111111","something@isep.pt","111111111111111111",role);

        //Act
        store.add(em1);

        //Assert
        Assert.assertTrue(store.contains(em1));
    }
    @Test
    public void containsFalse() {
        //Assert
        EmployeeStore store = new EmployeeStore();
        Employee em1 = new Employee("B00001","Bino", "casa", "91111111111","something@isep.pt","111111111111111111",role);
        Employee em2 = new Employee("B00001","Bino", "casa", "91111111111","something@isep.pt","111111111111111111",role);
        //Act
        store.add(em1);

        //Assert
        Assert.assertFalse(store.contains(em2));

    }
    @Test
    public void saveEmployeeValid() {
        //Arrange
        EmployeeStore store = new EmployeeStore();
        Employee em  = store.CreateEmployee("Bino", "casa", "91111111111","something@isep.pt","111111111111111111","11111111111111",1);

        //Assert
        Assert.assertTrue(store.saveEmployee());

    }
    @Test
    public void saveEmployeeInvalid() {
        //Arrange
        EmployeeStore store = new EmployeeStore();
        Employee em  = store.CreateEmployee("Bino", "casa", "91111111111","something@isep.pt","111111111111111111","11111111111111",1);
        store.add(em);
        Employee em1  = store.CreateEmployee("Bina", "casa", "91111111112","something@marcin.pl","11111111111111112","11111111111112",2);
        store.add(em1);
        //Assert
        Assert.assertFalse(store.saveEmployee());

    }
    @Test
    public void toStringTest(){
        EmployeeStore store = new EmployeeStore();
        Employee em1  = store.CreateEmployee("Bino", "casa", "91111111111","something@isep.pt","111111111111111111","11111111111111",1);
        Employee em2  = store.CreateEmployee("Bina", "casa", "91111111112","something@marcin.pl","11111111111111112","11111111111112",2);

        store.add(em1);
        store.add(em2);

        String actual = store.toString();

        String expected = em1.toString()+ "\n"+em2.toString()+"\n";

        Assert.assertEquals(actual,expected);
    }




}