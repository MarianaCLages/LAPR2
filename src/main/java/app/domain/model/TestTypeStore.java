package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TestTypeStore {
    List<TypeTest> array;
    TypeTest t;

    public TestTypeStore() {
        this.array = new ArrayList<TypeTest>();
    }

    public boolean CreateTestType(String testID,String description, String collectingMethod) {
        this.t = new TypeTest(testID, description, collectingMethod);
        if (ValidateTestType(t)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ValidateTestType(TypeTest t) {
        if (t == null && !contains(t)) {
            return false;
        }
        return true;
    }

    public boolean contains(TypeTest t) {
        if (this.array.contains(t)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean saveTestType(TypeTest t) {
        if (ValidateTestType(t)) {
            add(t);
            return true;
        } else {
            return false;
        }
    }

    public boolean add(TypeTest t) {
        array.add(t);
        return true;
    }

}
