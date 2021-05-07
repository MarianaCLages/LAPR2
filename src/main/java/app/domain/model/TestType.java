package app.domain.model;

import org.apache.commons.lang3.StringUtils;

public class TestType {
    private String testID;
    private String description;
    private String collectingMethod;
    private ParameterCategoryStore catStore;

    public TestType(String testID, String description, String collectingMethod, ParameterCategoryStore catStore){
        checkCodeRules(testID);
        checkCollectingMethodRules(collectingMethod);
        checkDescriptionRules(description);
        checkCategoriesList(catStore);
        this.testID=testID;
        this.description=description;
        this.collectingMethod=collectingMethod;
        this.catStore=catStore;
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
    private void checkCategoriesList(ParameterCategoryStore catStore){
        if (catStore.isEmpty())
            throw new IllegalArgumentException("Collecting method cannot be blank.");
    }

    @Override
    public String toString() {
        return "TestType{" + "testID=" + testID + '\n' +
                "description=" + description + '\n' +
                "collectingMethod=" + collectingMethod + '\n' +
                "categories:\n          " + catStore +
                '}';
    }

    public String getTestID() {
        return testID;
    }

    public ParameterCategoryStore getCatStore() {
        return catStore;
    }
}


