package app.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Class that represents an List of all the Parameters in the system
 */
public class ParameterStore {
    List<Parameter> array;
    Parameter pc;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of Parameter
     */
    public ParameterStore() {
        this.array = new ArrayList<Parameter>();
    }

    /**
     * This method creates a new Parameter object by calling his constructor
     *
     * @param code unique code needed to identify the Parameter
     * @param name short name that characterize the Parameter
     * @return boolean value that indicates if the object created is valid or not
     */
    public Parameter CreateParameter(String code, String name, String description, ParameterCategory cat) {
        this.pc = new Parameter(code, name, description, cat);
        return this.pc;
    }

    /**
     * this method checks if the Parameter object received is not null and if don't already exists.in the ArrayList
     *
     * @param pc Parameter object
     * @return boolean value that is true if the object is not null and don't already exists in the ArrayList
     */

    public boolean ValidateParameter(Parameter pc) {
        if (pc == null || contains(pc)) {
            return false;
        }
        return true;
    }

    /**
     * this method checks if the Parameter object received already exits in the ArrayList
     *
     * @param pc Parameter object
     * @return boolean value that is true if the object already exists in the ArrayList
     */

    public boolean contains(Parameter pc) {
        if (this.array.contains(pc)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method is used to save the Parameter object in the arrayList already created, before adding the object teh method validates it
     *
     * @return a boolean value that indicates the success of the operation
     */
    public boolean saveParameter() {
        if (ValidateParameter(this.pc)) {
            add(pc);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method adds the Parameter object to the arrayList
     *
     * @param pc Parameter object
     * @return a boolean value that indicates the success of the operation
     */

    public boolean add(Parameter pc) {
        array.add(pc);
        return true;
    }

    /**
     * returns an Parameter instance stored in the store given the index of it
     * @param index position o the object in the ArrayList
     * @return Parameter instance stored in the given index
     */
    public Parameter get(int index) {
        return array.get(index);
    }


    /**
     * Go through all the objects in the ArrayList and appends the String of the method toString to a new String creating a new line for object
     * @return String with all the objects in the ArrayList
     */
    public String toString() {
        StringBuilder listString = new StringBuilder();

        for (Parameter s : array) {
            listString.append(s.toString()).append("\n");
        }
        return String.valueOf(listString);
    }

    /**
     * @return instance of Parameter that was created
     */
    public Parameter getPc() {
        return pc;
    }
}
