package app.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 */
public class ParameterCategoryStore {
    List<ParameterCategory> array;
    ParameterCategory pc;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of ParameterCategory
     */
    public ParameterCategoryStore() {
        this.array = new ArrayList<ParameterCategory>();
    }

    /**
     * This method creates a new ParameterCategory object by calling his constructor. It also validates the object using the method ValidateParameterCategory implemented by this class
     *
     * @param code unique code needed to identify the Parameter Category
     * @param name short name that characterize the Parameter Category
     * @return boolean value that indicates if the object created is valid or not
     */
    public boolean CreateParameterCategory(String code, String name) {
        this.pc = new ParameterCategory(code, name);
        if (ValidateParameterCategory(pc)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method checks if the ParameterCategory object received is not null and if don't already exists.in the ArrayList
     *
     * @param pc ParameterCategory object
     * @return boolean value that is true if the object is not null and dont already exists in the ArrayList
     */

    public boolean ValidateParameterCategory(ParameterCategory pc) {
        if (pc == null && !contains(pc)) {
            return false;
        }
        return true;
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
     * this method is used to save the ParameterCategory object in the arrayList already created, before adding the object teh method validates it
     *
     * @param pc ParameterCategory object
     * @return a boolean value that indicates the success of the operation
     */
    public boolean saveParameterCategory(ParameterCategory pc) {
        if (ValidateParameterCategory(pc)) {
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
        array.add(pc);
        return true;
    }


    public ParameterCategory get(int index) {
        return array.get(index);
    }


    public ParameterCategory getByCode(String code) {
        for (ParameterCategory pc : array) {
            if (pc.getCode().equals(code)) {
                return pc;
            }
        }
        return null;
    }

    public ParameterCategory getByName(String name) {
        for (ParameterCategory pc : array) {
            if (pc.getName().equals(name)) {
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
