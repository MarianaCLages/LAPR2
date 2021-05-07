package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TestTypeStore {
    List<TestType> array;
    List<ParameterCategory> listCat;
    TestType t;

    public TestTypeStore() {
        this.array = new ArrayList<TestType>();
    }

    public boolean CreateTestType(String testID,String description, String collectingMethod, ParameterCategoryStore cat) {
        this.t = new TestType(testID, description, collectingMethod, cat);
        if (ValidateTestType(t) && !alreadyUsedID(t)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty(){
        return this.array.isEmpty();
    }

    public boolean ValidateTestType(TestType t) {
        if (t == null && !contains(t) ) {
            return false;
        }
        return true;
    }

    public boolean alreadyUsedID(TestType t){
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
