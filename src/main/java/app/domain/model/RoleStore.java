package app.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent the list of all the roles of the system
 */
public class RoleStore {


    private final Role ClinicalChemistryTechnologist = new Role("0", "Clinical Chemistry Technologist");
    private final Role MedicalLabTechnician = new Role("1", "Medical Lab Technician");
    private final Role LaboratoryCoordinator = new Role("2", "LaboratoryCoordinator");
    private final Role Receptionist = new Role("3", "Receptionist");
    private final Role SpecialistDoctor = new Role("4", "SpecialistDoctor");
    private List<Role> array;
    private Role pc;

    /**
     * Constructor of the class it creates an empty list and fills it with the roles of the Company
     */
    public RoleStore() {
        this.array = new ArrayList<>();

        array.add(ClinicalChemistryTechnologist);
        array.add(MedicalLabTechnician);
        array.add(LaboratoryCoordinator);
        array.add(Receptionist);
        array.add(SpecialistDoctor);
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



