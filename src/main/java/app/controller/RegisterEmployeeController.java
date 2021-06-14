package app.controller;


import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.stores.EmployeeStore;

/**
 * Controller of the UserStory : Register a new employee
 */
public class RegisterEmployeeController {


    private final Company company;
    private EmployeeStore store;

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

    }

    /**
     * Creates a new Employee  instance, firstly creates a instance of EmployeeStore and then call the method of this instance that creates the Employee instance
     *
     * @param name              name of the Employee
     * @param address           address of the Employee
     * @param phonenumber       Phone number of the Employee
     * @param email             email of the Employee
     * @param soc               standard occupation code of the Employee
     * @param doctorIndexNumber Doctor Index Number of the Specialist Doctor
     * @param role              integer that indicates the role of the employee
     */
    public void createEmployee(String name, String address, String phonenumber, String email, String soc, String doctorIndexNumber, int role) {
        store.createEmployee(name, address, phonenumber, email, soc, doctorIndexNumber, role);
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
        boolean saved;

        saved = store.saveEmployee();
        System.out.println(store.addUserWithRole(this.company));
        company.saveCompany();

        return saved;
    }
}

