package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.stores.EmployeeStore;

import java.util.List;

/**
 * Controller of the UserStory : Register a new employee
 */
public class RegisterEmployeeController {


    private Company company;
    private Employee em;
    private EmployeeStore store;
    private List roleList;

    /**
     * Constructor of the class, gets an instance of the company class
     */
    public RegisterEmployeeController() {

        this(App.getInstance().getCompany());
        store = company.getEmployeeList();

    }

    /**
     * Constructor of the class, receives an instance of the company class
     *
     * @param company instance of Company
     */
    public RegisterEmployeeController(Company company) {
        this.company = company;
        this.em = null;

    }

    /**
     * Creates a new Employee  instance, firstly creates a instance of EmployeeStore and then call the method of this instance that creates the Employee instance
     * @param name name of the Employee
     * @param address address of the Employee
     * @param phonenumber Phone number of the Employee
     * @param email email of the Employee
     * @param SOC standard occupation code of the Employee
     * @param DoctorIndexNumber Doctor Index Number of the Specialist Doctor
     * @param Role integer that indicates the role of the employee
     */
    public void createEmployee(String name, String address, String phonenumber, String email, String SOC, String DoctorIndexNumber, int Role) {
        store.CreateEmployee(name, address, phonenumber, email, SOC, DoctorIndexNumber, Role);
    }

    /**
     * @return String that represents the Employee instance
     */
    public Employee getEm() {
        return store.getEm();
    }

    /**
     * Calls the method of the instance of EmployeeStore that saves the Employee instance in the ArrayList, after saving Calls the method of the instance of EmployeeStore that adds an new user to the system
     *
     * @return success of the operation
     */
    public boolean saveEmployee() {
        boolean saved = false;

        saved = store.saveEmployee();
        store.addUserWithRole(this.company);

        return saved;
    }
}

