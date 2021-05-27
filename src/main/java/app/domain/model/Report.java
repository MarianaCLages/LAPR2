package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;


public class Report {

    private String testID;
    private String diagnosis;

    @Override
    public String toString() {
        return "The report for the test "+ testID+" is: \n" + diagnosis;
    }

    public Report(String testID, String diagnosis) {
        checkDiagnosisRules(diagnosis);
        this.testID = testID;
        this.diagnosis = diagnosis;
    }


    private void checkDiagnosisRules(String diagnosis) {

        if (StringUtils.isBlank(diagnosis)){
            throw new IllegalArgumentException("Diagnosis cannot be blank.");
    }

        if (diagnosis.length() >= Constants.DIAGNOSIS_MAX_LENGHT){
            throw new IllegalArgumentException("Diagnosis cannot exceed 200 char.");
        }

    }

    public String getTestID() {
        return testID;
    }


    public String getDiagnosis() {
        return diagnosis;
    }


}
