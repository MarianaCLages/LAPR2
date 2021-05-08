package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TestTypeStore {
    List<TestType> array;
    TestType t;

    public TestTypeStore() {
        this.array = new ArrayList<>();
    }

    public TestType CreateTestType(String testID, String description, String collectingMethod, ParameterCategoryStore catStore) {
        this.t = new TestType(testID, description, collectingMethod, catStore);
        return this.t;
    }

    public boolean ValidateTestType(TestType t) {
        if (t == null || contains(t)|| !uniqueID(t)) {
            return false;
        }
        return true;
    }

    public boolean uniqueID(TestType t) {
        boolean find = true;
        for (TestType t1:array) {
            if (t.getTestID().equals(t1.getTestID())){
                find = false;
            }
        }
        return find;
    }


    public boolean contains(TestType t) {
        if (this.array.contains(t)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean saveTestType() {
        if (ValidateTestType(this.t)) {
            add(t);
            return true;
        } else {
            return false;
        }
    }

    public boolean add(TestType t) {
        return array.add(t);
    }


    public TestType getByID(String id) {
        for (TestType t : array) {
            if (t.getTestID().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public boolean isEmpty(){return array.isEmpty();}

    public TestType getTestT() {
        return t;
    }
}
