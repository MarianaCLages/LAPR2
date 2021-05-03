package app.domain.model;

import org.apache.commons.lang3.StringUtils;

public class Parameter {
    private String name;
    private String code;
    private String description;
    private ParameterCategory cat;


    /**
     * Constructor of the Parameter, it calls 2 methods in order to validate the parameters
     *
     * @param code unique code needed to identify the Parameter
     * @param name short name that characterize the Parameter
     * @param cat
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
        if (code.length() != 5)
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
        if (name.length() > 8)
            throw new IllegalArgumentException("Name must have 8 chars.");


        name = name.toLowerCase();
        char[] charArray = name.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= 'a' && c <= 'z')) {
                throw new IllegalArgumentException("Name only accepts letters");
            }
        }
    }


    private void checkDescriptionRules(String description) {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if (description.length() > 20)
            throw new IllegalArgumentException("Description must have 20 chars.");
    }


    @Override
    public String toString() {
        return "Parameter{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + cat.toString() + '\'' +
                '}';
    }

    /**
     * Returns the code of the Parameter
     *
     * @return code: unique code needed to identify the Parameter
     */

    public String getCode() {
        return code;
    }

    /**
     * Returns the name of the Parameter
     *
     * @return name: unique name needed to identify the Parameter
     */
    public String getName() {
        return name;
    }

    /**
     * modifies the code of the Parameter
     *
     * @param name unique name needed to identify the Parameter
     */
    public void setName(String name) {
        this.name = name;
    }
}
