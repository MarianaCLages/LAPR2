package app.domain.mappers.dto;

import app.domain.model.Test;

import java.io.Serializable;

public class TestStoreDTO implements Serializable {
    private Test test;

    public TestStoreDTO(Test test){
        this.test=test;
    }

    @Override
    public String toString() {
        return "TestStoreDTO{" +
                "test=" + test +
                '}';
    }
}
