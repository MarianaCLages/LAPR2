package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeStore {
    List<Employee> array;
    Employee em;

    public EmployeeStore() {
        this.array = new ArrayList<Employee>();
    }



    public Employee CreateEmployee(String name, String address, String phonenumber, String email, String SOC,String DoctorIndexNumber, Role role) {

        if (role.getRoleID().equals("5")){
            this.em = new SpecialistDoctor(name, address, phonenumber, email,SOC,DoctorIndexNumber ,role);
        }else{
            this.em = new Employee(name, address, phonenumber, email, SOC, role);
        }
        return this.em;
    }

    public boolean ValidateEmployee(Employee em) {
        if (em == null || contains(em)) {
            return false;
        }
        return true;
    }
    public boolean contains(Employee em) {
        if (this.array.contains(em)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean saveEmployee() {
        if (ValidateEmployee(this.em)) {
            add(em);
            return true;
        } else {
            return false;
        }
    }
    public boolean add(Employee em) {
        array.add(em);
        return true;
    }
    public Employee get(int index) {
        return array.get(index);
    }

    public String toString() {
        StringBuilder listString = new StringBuilder();

        for (Employee s : array) {
            listString.append(s.toString()).append("\n");
        }
        return String.valueOf(listString);
    }

    public Employee getEm() {
        return em;
    }




}


