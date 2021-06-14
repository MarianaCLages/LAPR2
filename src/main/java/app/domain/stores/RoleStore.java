package app.domain.stores;

import app.domain.model.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent the list of all the roles of the system
 */
public class RoleStore implements Serializable {

    private final List<Role> array;

    /**
     * Constructor of the class it creates an empty list and fills it with the roles of the Company
     */
    public RoleStore() {
        this.array = new ArrayList<>();

        Role clinicalChemistryTechnologist = new Role("0", "Clinical Chemistry Technologist");
        array.add(clinicalChemistryTechnologist);
        Role medicalLabTechnician = new Role("1", "Medical Lab Technician");
        array.add(medicalLabTechnician);
        Role laboratoryCoordinator = new Role("2", "LaboratoryCoordinator");
        array.add(laboratoryCoordinator);
        Role receptionist = new Role("3", "Receptionist");
        array.add(receptionist);
        Role specialistDoctor = new Role("4", "SpecialistDoctor");
        array.add(specialistDoctor);
    }


    /**
     * This method search for an Role object by the index of that object in the ArrayList
     *
     * @param index index of the array list where we want to get the object
     * @return the Role object that was in the index of the array list
     */
    public Role get(int index) {
        return array.get(index);
    }


    /**
     * @return the list of Roles
     */
    public List<Role> getRoleList() {

        return this.array;
    }
}



