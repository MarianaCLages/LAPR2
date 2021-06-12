package app.domain.model;

import app.domain.shared.Constants;
import app.domain.stores.ParameterCategoryStore;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * Class that represents an Type of Test
 */
public class TestType implements Serializable {
    private String testID;
    private String description;
    private String collectingMethod;
    private ParameterCategoryStore catStore;
    private String externalModule;

    /**
     * Constructor of TestType, it calls methods in order to validate the parameters
     *
     * @param testID           ID of Type if test
     * @param description      simple description of the type of test
     * @param collectingMethod collecting methods of the type of test
     * @param catStore         list of Parameter Categories associated with the test
     */
    public TestType(String testID, String description, String collectingMethod, ParameterCategoryStore catStore) {
        checkCodeRules(testID);
        checkCollectingMethodRules(collectingMethod);
        checkDescriptionRules(description);
        checkCategoriesList(catStore);
        this.testID = testID;
        this.description = description;
        this.collectingMethod = collectingMethod;
        this.catStore = catStore;
        this.externalModule = setExternalModule(testID);

    }

    private String setExternalModule(String testID) {
        Properties props = new Properties();
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }


        if (testID.equals("BL000")) {
            externalModule = props.getProperty("blood.API");
        } else {
            if (testID.equals("COV19")) {
                externalModule = props.getProperty("covid.API");
            } else {
                throw new IllegalArgumentException("There is no external module in the System associated with this type of test");
            }
        }
        return externalModule;
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param testID identification code of the test
     */
    private void checkCodeRules(String testID) {
        if (StringUtils.isBlank(testID))
            throw new IllegalArgumentException("Code cannot be blank.");
        if (testID.length() != Constants.MAX_CODE)
            throw new IllegalArgumentException("Code must have 5 alphanumeric chars.");
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param description description of the test
     */
    private void checkDescriptionRules(String description) {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if (description.length() > Constants.MAX_DESCRIPTION)
            throw new IllegalArgumentException("Description must have, at maximum, 15 chars.");
    }


    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param collectingMethod collecting method of the test
     */
    private void checkCollectingMethodRules(String collectingMethod) {
        if (StringUtils.isBlank(collectingMethod))
            throw new IllegalArgumentException("Collecting method cannot be blank.");
        if (collectingMethod.length() > Constants.MAX_COLLECTING_METHODS)
            throw new IllegalArgumentException("Collecting method must have, at maximum, 20 chars.");
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param catStore the list of categories of the test
     */
    private void checkCategoriesList(ParameterCategoryStore catStore) {
        if (catStore.isEmpty())
            throw new IllegalArgumentException("Collecting method cannot be blank.");
    }

    /**
     * @return A string with the format "TestType: testID= testID, description= description, collectingMethod= collectingMethod, categories: (list of categories)";
     */
    @Override
    public String toString() {
        return "TestType: " + "testID=" + testID + ", description=" + description + ", collectingMethod=" + collectingMethod + ", categories: " + catStore;
    }

    /**
     * @return Id that characterizes the test
     */
    public String getTestID() {
        return testID;
    }

    /**
     * @return List of categories associated with the test
     */
    public ParameterCategoryStore getCatStore() {
        return catStore;
    }

    /**
     * @return String with the name of the description associated with the testType
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return String with the collecting method associated with the testType
     */
    public String getCollectingMethod() {
        return collectingMethod;
    }

    /**
     * @return String with the name of the external module associated with the testType
     */
    public String getExternalModule() {
        return externalModule;
    }


}


