package app.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 */
public class ParameterCategoryStore {
    List<ParameterCategory> array;
    private ParameterCategory pc;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of ParameterCategory
     */
    public ParameterCategoryStore() {
        this.array = new ArrayList<>();
    }

    /**
     * This method creates a new ParameterCategory object by calling his constructor.
     *
     * @param code unique code needed to identify the Parameter Category
     * @param name short name that characterize the Parameter Category
     * @return Parameter Category object created
     */
    public ParameterCategory CreateParameterCategory(String code, String name) {

        this.pc = new ParameterCategory(code, name);
        return this.pc;
    }

    /**
     * this method checks if the ParameterCategory object received is not null, if don't already exists.in the ArrayList and if the ParameterCategory code dont already exist
     *
     * @param pc ParameterCategory object
     * @return boolean value that is true if the object is not null and dont already exists in the ArrayList
     */

    public boolean ValidateParameterCategory(ParameterCategory pc) {
        if (pc == null || contains(pc) || !uniqueCode(pc)) {
            return false;
        }
        return true;
    }

    /**
     * This method searches in the Array List if alredy exists a Parameter Category object with the same code
     * @param pc Parameter Category object in which we want to check the code
     * @return true if the code dont already exists, false if not
     */

    public boolean uniqueCode(ParameterCategory pc){
        boolean find = true;
        for (ParameterCategory pc1:array) {
            if (pc.getCode().equals(pc1.getCode())){
                find = false;
            }
        }
        return find;
    }

    /**
     * this method checks if the ParameterCategory object received already exits in the ArrayList
     *
     * @param pc ParameterCategory object
     * @return boolean value that is true if the object already exists in the ArrayList
     */

    public boolean contains(ParameterCategory pc) {
        if (this.array.contains(pc)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method is used to save the ParameterCategory object in the arrayList already created, before adding the object the method validates it
     *
     * @return a boolean value that indicates the success of the operation
     */
    public boolean saveParameterCategory() {
        if (ValidateParameterCategory(this.pc)) {
            add(pc);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method adds the ParameterCategory object to the arrayList
     *
     * @param pc ParameterCategory object
     * @return a boolean value that indicates the success of the operation
     */

    public boolean add(ParameterCategory pc) {
        return array.add(pc);
    }

    /**
     * This method search for an Parameter Category object by the index of that object in the ArrayList
     * @param index index of the array list where we want to get the object
     * @return the Parameter Category object that was in the index of the array list
     */

    public ParameterCategory get(int index) {
        return array.get(index);
    }

    /**
     * This method search for an Parameter Category object by the code of that object in the ArrayList
     * @param code code that characterize the Parameter Category object
     * @return if the object is found it returns the object, if not it returns null
     */

    public ParameterCategory getByCode(String code) {
        for (ParameterCategory pc : array) {
            if (pc.getCode().equals(code)) {
                return pc;
            }
        }
        return null;
    }



    public String toString() {
        StringBuilder listString = new StringBuilder();

        for (ParameterCategory s : array) {
            listString.append(s.toString()).append("\n");
        }
        return String.valueOf(listString);
    }

    public ParameterCategory getPc() {
        return pc;
    }
}