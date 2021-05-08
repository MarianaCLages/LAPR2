package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLabStore {
    List<ClinicalAnalysisLab> array;
    ClinicalAnalysisLab cal;


    public ClinicalAnalysisLabStore() {
        this.array = new ArrayList<ClinicalAnalysisLab>();
    }

    public ClinicalAnalysisLab CreateClinicalAnalysisLab(String name, String address, String id, String tin, String phoneNumber, TestType tType) {
        this.cal = new ClinicalAnalysisLab(name, address, id, tin, phoneNumber, tType);
        return this.cal;
    }

    public boolean ValidateClinicalAnalysisLab(ClinicalAnalysisLab cal) {
        if (cal == null || contains(cal)) {
            return false;
        }
        return true;
    }


    public boolean contains(ClinicalAnalysisLab cal) {
        if (this.array.contains(cal)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean saveClinicalAnalysisLab() {
        if (ValidateClinicalAnalysisLab(this.cal)) {
            add(cal);
            return true;
        } else {
            return false;
        }
    }

    public boolean add(ClinicalAnalysisLab cal) {
        array.add(cal);
        return true;
    }
    public ClinicalAnalysisLab get(int index) {
        return array.get(index);
    }

    public String toString() {
        StringBuilder listString = new StringBuilder();

        for (ClinicalAnalysisLab s : array) {
            listString.append(s.toString()).append("\n");
        }
        return String.valueOf(listString);
    }
    public ClinicalAnalysisLab getCal() {
        return cal;
    }

}