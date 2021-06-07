package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Class that represents a Report
 */
public class Report implements Serializable {

    private final String testID;
    private final String diagnosis;


    /**
     * Constructor of the Report, it calls methods in order to validate the parameters
     *
     * @param testID id of the test
     * @param diagnosis text contained in the report
     */
    public Report(String testID, String diagnosis) {
        checkDiagnosisRules(diagnosis);
        this.testID = testID;
        this.diagnosis = diagnosis;
    }

    /**
     * @return a string with the following format: "The report for the test" testID "is:" diagnosis.
     */
    @Override
    public String toString() {
        return "The report for the test " + testID + " is: \n" + diagnosis;
    }


    /**
     * This method checks if the Diagnosis provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param diagnosis text contained in the report
     */
    private void checkDiagnosisRules(String diagnosis) {

        if (StringUtils.isBlank(diagnosis)) {
            throw new IllegalArgumentException("Diagnosis cannot be blank.");
        }

        if (diagnosis.length() > Constants.DIAGNOSIS_MAX_LENGHT) {
            throw new IllegalArgumentException("Diagnosis cannot exceed 200 char.");
        }

    }

    /**
     * @return a string with the Test ID
     */
    public String getTestID() {
        return testID;
    }

    /**
     * @return a string with the Diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }


}
