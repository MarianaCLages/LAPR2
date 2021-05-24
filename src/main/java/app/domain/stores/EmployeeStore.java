package app.domain.stores;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.RoleStore;
import app.domain.model.SpecialistDoctor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an List of all the Category of Parameters in the system
 */
public class EmployeeStore {
    private List <Employee> array;
    private Employee em;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of Employee
     */
    public EmployeeStore() {
        this.array = new ArrayList<Employee>();
    }


    /**
     * This method creates a new Employee object by calling his constructor, depending of the role it crates an instance of employee or of specialist doctor that is a subclass of employee
     * @param name name of the Employee
     * @param address address of the Employee
     * @param phonenumber Phone number of the Employee
     * @param email email of the Employee
     * @param SOC standard occupation code of the Employee
     * @param DoctorIndexNumber Doctor Index Number of the Specialist Doctor
     * @param role integer that indicates the role of the employee
     * @return Employee instance created
     */
    public Employee CreateEmployee(String name, String address, String phonenumber, String email, String SOC, String DoctorIndexNumber, int role) {

        RoleStore roles = new RoleStore();

        if (role == 5){
            this.em = new SpecialistDoctor(CreateEmployeeID(name),name, address, phonenumber, email,SOC,DoctorIndexNumber ,roles.get(role));
        }else{
            this.em = new Employee(CreateEmployeeID(name), name, address, phonenumber, email, SOC, roles.get(role));
        }
        return this.em;
    }

    /**
     * Creates a employeeID from the name of the employee and his location in the list of employees
     * @param name Name of the Employee
     * @return The EmployeeID
     */
    private String CreateEmployeeID(String name){

        int ID = array.size() + 1;
        String EmployeeNumberID = String.valueOf(ID);
        String EmployeeNameID = "";
        String empty;

        for (int i = 0; i < name.length(); i++) {
            if (Character.isUpperCase(name.charAt(i))) {
                EmployeeNameID += name.charAt(i);
            }
        }
        empty = "" + ID;
        while(empty.length() < 5){
            empty = "0" + empty;
        }
        String EmployeeID = EmployeeNameID+empty;

        return EmployeeID;
    }

    /**
     * this method checks if the Employee object received is not null and if don't already exists in the ArrayList
     * @param em instance of Employee
     * @return boolean value that is true if the object is not null and don't already exists in the ArrayList
     */
    public boolean ValidateEmployee(Employee em) {
        return em != null && !contains(em) && !exists(em);
    }

    private boolean exists(Employee em) {
        for (Employee e : array) {
            return e.getEmail().equals(em.getEmail()) || e.getPhonenumber().equals(em.getPhonenumber());
        }
        return false;
    }

    /**
     * This method checks if the Employee object received already exits in the ArrayList
     * @param em instance of Employee
     * @return boolean value that is true if the object already exists in the ArrayList
     */
    public boolean contains(Employee em) {
        if (this.array.contains(em)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * his method is used to save the Employee object in the arrayList already created, before adding the object teh method validates it
     * @return a boolean value that indicates the success of the operation
     */
    public boolean saveEmployee() {
        if (ValidateEmployee(this.em)) {
            array.add(this.em);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method adds the Parameter object to the arrayList
     * @param em instance of Employee
     * @return a boolean value that indicates the success of the operation
     */
    public boolean add(Employee em) {
        array.add(em);
        return true;
    }

    /**
     * returns an Parameter instance stored in the store given the index of it
     * @param index  position o the object in the ArrayList
     * @return Employee instance stored in the given index
     */
    public Employee get(int index) {
        return array.get(index);
    }

    /**
     * Go through all the objects in the ArrayList and appends the String of the method toString to a new String creating a new line for object
     * @return String with all the objects in the ArrayList
     */
    public String toString() {
        StringBuilder listString = new StringBuilder();

        for (Employee s : array) {
            listString.append(s.toString()).append("\n");
        }
        return String.valueOf(listString);
    }

    /**
     * Adds user to the system by calling the Employee instance method
     * @param company instance of company class in order to be able to get the AuthFacade class that is associated with the system
     * @return boolean value that represents the success of the operation
     */
    public boolean addUserWithRole(Company company) {
        return em.addUserWithRole(company);
    }

    /**
     *
     * @return the instance of client
     */
    public Employee getEm() {
        return this.em;
    }




}


