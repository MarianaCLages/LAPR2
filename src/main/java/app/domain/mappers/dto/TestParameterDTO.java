package app.domain.mappers.dto;

import java.io.Serializable;

public class TestParameterDTO implements Serializable {
    private String pcode;

    public TestParameterDTO(String pcode) {
        this.pcode = pcode;
    }

    public String getPcode() {
        return pcode;
    }

    @Override
    public String toString() {
        return "TestParameter: " + "pcode= " + pcode;
    }
}
