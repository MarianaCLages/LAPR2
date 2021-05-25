package app.domain.stores;

import app.domain.model.Test;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class TestStore {
    private List<Test> array;
    private Test t;


    public TestStore() {
        this.array = new ArrayList<>();
    }

    public boolean createTest(String testNhsNumber, String clientCc, TestType testType, ParameterCategoryStore catList, ParameterStore paList) {

        this.t = new Test(getTestId(), testNhsNumber, clientCc, testType, catList, paList);
        this.t.addTestParameter();

        return validateTest();
    }

    public String getTestId() {
        int ID = this.array.size() + 1;
        StringBuilder testNumber = new StringBuilder(String.valueOf(ID));

        while (testNumber.length() < 15) {
            testNumber.insert(0, "0");
        }

        return testNumber.toString();
    }

    public boolean validateTest() {
        return this.t != null && !contains(this.t) && !exists(this.t);
    }

    private boolean exists(Test t) {
        for (Test t1 : this.array) {
            if (t.getTestNhsNumber().equals(t1.getTestNhsNumber())) {
                return true;
            }
        }
        return false;
    }

    private boolean contains(Test t) {
        return array.contains(t);
    }

    public boolean saveTest() {
        if (validateTest()) {
            array.add(this.t);
            return true;
        }
        return false;
    }

    public String getTest(){
        return this.t.toString();
    }


}
