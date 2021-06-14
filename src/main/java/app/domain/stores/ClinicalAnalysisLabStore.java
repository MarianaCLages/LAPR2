package app.domain.stores;

import app.domain.model.ClinicalAnalysisLab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an List of all the Clinical Analysis Lab in the system
 */
public class ClinicalAnalysisLabStore implements Serializable {

    private final List<ClinicalAnalysisLab> array;
    private ClinicalAnalysisLab cal;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of Clinical Analysis Lab
     */
    public ClinicalAnalysisLabStore() {
        this.array = new ArrayList<>();
    }

    /**
     * This method creates a new Clinical Analysis Lab object by calling his constructor
     *
     * @param name        name of the Clinical Analysis Lab
     * @param address     address of the Clinical Analysis Lab
     * @param id          id of Clinical Analysis Lab
     * @param tin         TIN of Clinical Analysis Lab
     * @param phoneNumber Phone Number Clinical Analysis Lab
     * @param tType       list of Test Types associated with the test
     * @return Clinical Analysis Lab object created
     */
    public ClinicalAnalysisLab createClinicalAnalysisLab(String name, String address, String id, String tin, String phoneNumber, TestTypeStore tType) {
        this.cal = new ClinicalAnalysisLab(name, address, id, tin, phoneNumber, tType);
        return this.cal;
    }

    /**
     * this method checks if the Clinical Analysis Lab object received is not null, if don't already exists in the ArrayList
     *
     * @param cal Clinical Analysis Lab object
     * @return boolean value that is true if the object is not null and don't already exists in the ArrayList
     */
    public boolean validateClinicalAnalysisLab(ClinicalAnalysisLab cal) {
        return cal != null && !contains(cal);
    }

    /**
     * this method checks if the Clinical Analysis Lab object received already exits in the ArrayList
     *
     * @param cal Clinical Analysis Lab object
     * @return boolean value that is true if the object already exists in the ArrayList
     */

    public boolean contains(ClinicalAnalysisLab cal) {
        return this.array.contains(cal);
    }

    /**
     * this method is used to save the Clinical Analysis Lab object in the arrayList already created, before adding the object the method validates it
     *
     * @return a boolean value that indicates the success of the operation
     */
    public boolean saveClinicalAnalysisLab() {
        if (validateClinicalAnalysisLab(this.cal)) {
            add(cal);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method adds the Clinical Analysis Lab object to the arrayList
     *
     * @param cal ParameterCategory object
     * @return a boolean value that indicates the success of the operation
     */
    public boolean add(ClinicalAnalysisLab cal) {
        array.add(cal);
        return true;
    }

    /**
     * This method search for an Clinical Analysis Lab object by the index of that object in the ArrayList
     *
     * @param index index of the array list where we want to get the object
     * @return the Clinical Analysis Lab object that was in the index of the array list
     */
    public ClinicalAnalysisLab get(int index) {
        return this.array.get(index);
    }

    /**
     * Go through all the objects in the ArrayList and appends the String of the method toString to a new String creating a new line for object
     *
     * @return String with all the objects in the ArrayList
     */
    public String toString() {
        StringBuilder listString = new StringBuilder();

        for (ClinicalAnalysisLab s : array) {
            listString.append(s.toString()).append("\n");
        }
        return String.valueOf(listString);
    }

    /**
     * @return Parameter Category object
     */
    public ClinicalAnalysisLab getCal() {
        return cal;
    }

    public boolean verifyClinicalLabID(String id) {
        for (ClinicalAnalysisLab c : this.array) {

            if (c.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

}