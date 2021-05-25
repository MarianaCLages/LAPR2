package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;


public class Report {

    private String TestID;
    private String Diagnosis;

    public Report(String TestID, String Diagnosis) {
        checkDiagnosisRules(Diagnosis);
        this.TestID = TestID;
        this.Diagnosis = Diagnosis;
    }


    private void checkDiagnosisRules(String Diagnosis) {

        if (StringUtils.isBlank(Diagnosis)){
            throw new IllegalArgumentException("Diagnosis cannot be blank.");
    }

        if (Diagnosis.length() <= Constants.DIAGNOSIS_MAX_LENGHT){
            throw new IllegalArgumentException("Diagnosis cannot exceed 200 char.");
        }

    }



}
