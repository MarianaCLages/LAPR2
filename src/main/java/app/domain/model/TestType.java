package app.domain.model;

//Each test type should have a set of categories.
//Each category should be chosen from a list of categories.
//Each category has a name and a unique code. There are no subcategories.
//There exists only one collection method per test type.

import org.apache.commons.lang3.StringUtils;

public class TestType {
    private String testID;
    private String description;
    private String collectingMethod;
    private ParameterCategoryStore categories;

    public TestType(String testID, String description, String collectingMethod, ParameterCategoryStore categories){
        checkCodeRules(testID);
        checkCollectingMethodRules(collectingMethod);
        checkDescriptionRules(description);
        checkCategoriesList(categories);
        this.testID=testID;
        this.description=description;
        this.collectingMethod=collectingMethod;
        this.categories=categories;
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

    private void checkCategoriesList(ParameterCategoryStore categories){
        if (categories.isEmpty())
            throw new IllegalArgumentException("Collecting method cannot be blank.");
    }



}


