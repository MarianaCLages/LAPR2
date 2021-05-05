package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TestTypeStore {
    List<TestType> array;
    TestType t;

    public TestTypeStore() {
        this.array = new ArrayList<TestType>();
    }

    public boolean CreateTestType(String testID,String description, String collectingMethod) {
        this.t = new TestType(testID, description, collectingMethod);
        if (ValidateTestType(t)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ValidateTestType(TestType t) {
        if (t == null && !contains(t)) {
            return false;
        }
        return true;
    }

    public boolean contains(TestType t) {
        if (this.array.contains(t)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean saveTestType(TestType t) {
        if (ValidateTestType(t)) {
            add(t);
            return true;
        } else {
            return false;
        }
    }

    public boolean add(TestType t) {
        array.add(t);
        return true;
    }

}
