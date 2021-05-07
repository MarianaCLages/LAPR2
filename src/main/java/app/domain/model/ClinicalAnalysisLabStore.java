package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLabStore {
    List<ClinicalAnalysisLab> array;
    ClinicalAnalysisLab cal;
    private ParameterCategoryStore cat = new ParameterCategoryStore();
    private TestTypeStore store = new TestTypeStore();

    public ClinicalAnalysisLabStore() {
        this.array = new ArrayList<ClinicalAnalysisLab>();
    }

    public boolean CreateClinicalAnalysisLab(String name, String address, String id, String tin, String phoneNumber, TestType tType) {
        this.cal = new ClinicalAnalysisLab(name, address, id, tin, phoneNumber, tType);
        if (ValidateClinicalAnalysisLab(cal)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ValidateClinicalAnalysisLab(ClinicalAnalysisLab cal) {
        if (cal == null && !contains(cal)) {
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

    public boolean saveClinicalAnalysisLab(ClinicalAnalysisLab cal) {
        if (ValidateClinicalAnalysisLab(cal)) {
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
}