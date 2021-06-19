package app.domain.stores;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;


/**
 * Class that represents an List of all the Parameters in the system
 */
public class ParameterStore implements Serializable {
    private final List<Parameter> array;
    private Parameter pc;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of Parameter
     */
    public ParameterStore() {
        this.array = new ArrayList<>();
    }

    /**
     * This method creates a new Parameter object by calling his constructor
     *
     * @param code unique code needed to identify the Parameter
     * @param name short name that characterize the Parameter
     * @return boolean value that indicates if the object created is valid or not
     */
    public Parameter createParameter(String code, String name, String description, ParameterCategory cat) {
        this.pc = new Parameter(code, name, description, cat);
        return this.pc;
    }

    /**
     * this method checks if the Parameter object received is not null and if don't already exists.in the ArrayList
     *
     * @param pc Parameter object
     * @return boolean value that is true if the object is not null and don't already exists in the ArrayList
     */

    public boolean validateParameter(Parameter pc) {
        return pc != null && !contains(pc);
    }

    /**
     * this method checks if the Parameter object received already exits in the ArrayList
     *
     * @param pc Parameter object
     * @return boolean value that is true if the object already exists in the ArrayList
     */

    public boolean contains(Parameter pc) {
        return this.array.contains(pc);
    }

    /**
     * this method is used to save the Parameter object in the arrayList already created, before adding the object teh method validates it
     *
     * @return a boolean value that indicates the success of the operation
     */
    public boolean saveParameter() {
        if (validateParameter(this.pc)) {
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
     *
     * @param index position o the object in the ArrayList
     * @return Parameter instance stored in the given index
     */
    public Parameter get(int index) {
        return array.get(index);
    }


    /**
     * Go through all the objects in the ArrayList and appends the String of the method toString to a new String creating a new line for object
     *
     * @return String with all the objects in the ArrayList
     */
    public String toString() {
        StringBuilder listString = new StringBuilder();
        listString.append("[");

        for (Parameter s : array) {
            listString.append(s.toString()).append(", ");
        }
        listString.deleteCharAt(listString.length() - 1);
        listString.deleteCharAt(listString.length() - 1);
        listString.append("]");

        return valueOf(listString);
    }

    /**
     * @return instance of Parameter that was created
     */
    public Parameter getPc() {
        return pc;
    }

    /**
     * @return boolean value that is true if the store is empty
     */
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * @return the list of all the parameters in the system
     */
    public List<Parameter> getList() {
        return array;
    }

    /**
     * returns a list of parameters associated with a category
     *
     * @param categoryCode the code of a category
     * @return list of parameters
     */
    public List<Parameter> getParameterList(String categoryCode) {

        List<Parameter> parameters = new ArrayList<>();

        for (Parameter p : this.array) {
            if (p.getCat().getCode().equals(categoryCode)) {
                parameters.add(p);
            }
        }
        return parameters;
    }

    /**
     * @param parameterCode the code of the parameter
     * @return parameter object with the given code
     */
    public Parameter getParameter(String parameterCode) {
        for (Parameter p : array) {
            if (p.getCode().equals(parameterCode)) {
                return p;
            }
        }
        return null;
    }

    public Parameter getParameterExist(String parameter) {
        for (Parameter p : this.array) {
            if (p.getName().equals(parameter)) {
                return p;
            }
        }
        return null;
    }

    public Parameter getParameterByCode(String code) {
        for (Parameter p1 : this.array) {
            if (p1.getCode().equals(code)) {
                return p1;
            }
        }
        return null;
    }

}
