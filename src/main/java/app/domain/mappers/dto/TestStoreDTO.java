package app.domain.mappers.dto;
import app.domain.model.Test;
public class TestStoreDTO {
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
