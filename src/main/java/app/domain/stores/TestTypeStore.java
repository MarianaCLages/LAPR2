package app.domain.stores;

import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an List of all the Types of Tests in the system
 */
public class TestTypeStore {
    List<TestType> array;
    TestType t;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of Parameter
     */
    public TestTypeStore() {
        this.array = new ArrayList<>();
    }

    /**
     * This method creates a new TestType object by calling his constructor
     * @param testID ID of Type if Test
     * @param description simple description of the type of test
     * @param collectingMethod collecting methods of the type of test
     * @param catStore list of Parameter Categories associated with the test
     * @return Type of Test created
     */
    public TestType CreateTestType(String testID, String description, String collectingMethod, ParameterCategoryStore catStore) {
        this.t = new TestType(testID, description, collectingMethod, catStore);
        return this.t;
    }

    /**
     * this method checks if the Test Type object received is not null, if don't already exists.in the ArrayList and if the Test Type code dont already exist
     * @param t TestType that is going to be validate
     * @return boolean value that is true if the object is not null and dont already exists in the ArrayList
     */
    public boolean ValidateTestType(TestType t) {
        return t != null && !contains(t) && uniqueID(t);
    }

    /**
     * This method searches in the Array List if already exists a Parameter Category object with the same code
     * @param t TestType object in which we want to check the code
     * @return true if the code dont already exists, false if not
     */
    public boolean uniqueID(TestType t) {
        boolean find = true;
        for (TestType t1 : array) {
            if (t.getTestID().equals(t1.getTestID())) {
                find = false;
            }
        }
        return find;
    }

    /**
     * this method checks if the TestType object received already exits in the ArrayList
     * @param t TestType object that we want to check
     * @return boolean value that is true if the object already exists in the ArrayList
     */
    public boolean contains(TestType t) {
        return this.array.contains(t);
    }

    /**
     * this method is used to save the TestType object in the arrayList already created, before adding the object the method validates it
     * @return a boolean value that indicates the success of the operation
     */
    public boolean saveTestType() {
        if (ValidateTestType(this.t)) {
            add(t);
            return true;
        } else {
            return false;
        }
    }
    /**
     * this method adds the ParameterCategory object to the arrayList
     *
     * @param t TestType object
     * @return a boolean value that indicates the success of the operation
     */
    public boolean add(TestType t) {
        return array.add(t);
    }

    /**
     * This method search for an TestType object by the code of that object in the ArrayList
     *
     * @param id ID that characterize the TestType object
     * @return if the object is found it returns the object, if not it returns null
     */
    public TestType getByID(String id) {
        for (TestType t1 : array) {
            if (t1.getTestID().equals(id)) {
                return t1;
            }
        }
        return null;
    }

    /**
     * @return  the list of Parameters Category
     */
    public List<TestType> getList() {
        return this.array;
    }

    /**
     * Checks if the ArrayList of TestType are empty
     * @return a boolean value that represents if the ArrayList is empty
     */
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * @return TestType object
     */
    public TestType getTestT() {
        return t;
    }
}
