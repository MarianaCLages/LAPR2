package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeStore {
    private List <Employee> array;
    private Employee em;

    public EmployeeStore() {
        this.array = new ArrayList<Employee>();
    }



    public Employee CreateEmployee(String name, String address, String phonenumber, String email, String SOC,String DoctorIndexNumber, int role) {

        RoleStore roles = new RoleStore();

        if (role == 4){
            this.em = new SpecialistDoctor(name, address, phonenumber, email,SOC,DoctorIndexNumber ,roles.get(role));
        }else{
            this.em = new Employee(name, address, phonenumber, email, SOC, roles.get(role));
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


