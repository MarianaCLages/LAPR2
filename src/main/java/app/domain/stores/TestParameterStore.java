package app.domain.stores;

import app.domain.model.TestParameter;

import java.util.ArrayList;
import java.util.List;

public class TestParameterStore {
    private List<TestParameter> store;
    private TestParameter tparam;


    public TestParameterStore() {
        this.store = new ArrayList<>();
    }

    public void createTestParameter(String code){
        this.tparam = new TestParameter(code);
    }

    public void addTestParameter(){
        this.store.add(this.tparam);
    }

}
