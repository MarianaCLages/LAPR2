package app.domain.mappers.dto;

import app.domain.model.Test;
import app.domain.stores.TestStore;

public class TestStoreDTO {
    private String code;
    private String id;

    public TestStoreDTO(Test test) {
        this.code = test.getTestCode();
        this.id = test.getID();
    }


}
