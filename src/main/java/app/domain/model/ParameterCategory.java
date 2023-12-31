package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Class that represents an Category of Parameters
 */
public class ParameterCategory implements Serializable {
    private final String code;
    private final String name;


    /**
     * Constructor of the ParameterCategory, it calls 2 methods in order to validate the parameters
     *
     * @param code unique code needed to identify the Parameter Category
     * @param name short name that characterize the Parameter Category
     */
    public ParameterCategory(String code, String name) {
        checkCodeRules(code);
        checkNameRules(name);
        this.code = code;
        this.name = name;
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param code unique code needed to identify the Parameter Category
     */
    private void checkCodeRules(String code) {
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("Code cannot be blank.");
        }
        if ((code.length() != Constants.MAX_CODE)) {
            throw new IllegalArgumentException("Code must have 5 chars.");
        }
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param name name tha identifies the Parameter Category
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (name.length() > Constants.MAX_CATEGORY_NAME) {
            throw new IllegalArgumentException("Name cannot have more than" + Constants.MAX_CATEGORY_NAME + "characters.");
        }
    }

    /**
     * @return A string with the format "Code = code Name= name" where "code" and "name" are the two parameter of the Parameter Category object
     */

    @Override
    public String toString() {
        return "Code = " + code + " Name=" + name;
    }

    /**
     * Returns the code of the Parameter Category
     *
     * @return code: unique code needed to identify the Parameter Category
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the name of the Parameter Category
     *
     * @return name
     */
    public String getName() {
        return name;
    }


}