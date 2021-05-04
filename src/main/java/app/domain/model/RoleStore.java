package app.domain.model;

public enum RoleStore {

    ClinicalChemistryTechnologist(1),MedicalLabTechnician(2),LaboratoryCoordinator(3),Receptionist(4),SpecialistDoctor(5),Administrator(6);

    private int index;

    RoleStore(int i) {
        this.index = i;
    }
    public int getRoleStore() {
        return this.index;
    }

}


