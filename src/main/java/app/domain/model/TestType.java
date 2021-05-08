package app.domain.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Class that represents an Type of Test
 */
public class TestType {
    private String testID;
    private String description;
    private String collectingMethod;
    private ParameterCategoryStore catStore;

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
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param testID identification code of the test
     */
    private void checkCodeRules(String testID) {
        if (StringUtils.isBlank(testID))
            throw new IllegalArgumentException("Code cannot be blank.");
        if (testID.length() != 5)
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
        if (description.length() > 16)
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
        if (collectingMethod.length() > 21)
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
        return "TestType: " + "testID=" + testID + ", description=" + description + ", collectingMethod=" + collectingMethod +", categories: " + catStore;
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
}


