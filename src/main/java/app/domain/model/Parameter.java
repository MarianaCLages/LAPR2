package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Class that represents an Parameter
 */
public class Parameter implements Serializable {
    private final String name;
    private final String code;
    private final String description;
    private final ParameterCategory cat;


    /**
     * Constructor of the Parameter, it calls 2 methods in order to validate the parameters
     *
     * @param code        unique code needed to identify the Parameter
     * @param description description that characterize the Parameter
     * @param name        short name that characterize the Parameter
     * @param cat         category associated with the Parameter
     */
    public Parameter(String code, String name, String description, ParameterCategory cat) {
        checkCodeRules(code);
        checkNameRules(name);
        checkDescriptionRules(description);
        this.code = code;
        this.name = name;
        this.description = description;
        this.cat = cat;
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param code unique code needed to identify the Parameter
     */
    private void checkCodeRules(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if (code.length() != Constants.MAX_CODE)
            throw new IllegalArgumentException("Code must have 5 chars.");
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param name name tha identifies the Parameter
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (name.length() > Constants.MAX_PARAMETER_NAME)
            throw new IllegalArgumentException("Name must have at least 8 chars.");
        name = name.toLowerCase();
        name = name.replace(" ", "");
        char[] charArray = name.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= 'a' && c <= 'z')) {
                throw new IllegalArgumentException("Name only accepts letters");
            }
        }
    }

    /**
     * This method checks if the description provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param description description that characterize the Parameter
     */
    private void checkDescriptionRules(String description) {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if (description.length() > Constants.MAX_PARAMETER_DESCRIPTION)
            throw new IllegalArgumentException("Description must have 20 chars.");
    }

    /**
     * @return "Parameter:code= code, name= name, description= description, category= cat;
     */
    @Override
    public String toString() {
        return "Parameter: " +
                "code=" + code +
                ", name=" + name +
                ", description=" + description +
                ", category=" + cat.toString();
    }

    /**
     * @return unique code needed to identify the Parameter
     */
    public String getCode() {
        return code;
    }

    /**
     * @return short name that characterize the Parameter
     */
    public String getName() {
        return name;
    }

    /**
     * @return category associated with the Parameter
     */
    public ParameterCategory getCat() {
        return cat;
    }
}
