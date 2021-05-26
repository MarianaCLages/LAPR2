package app.domain.model;

import com.example3.CovidReferenceValues1API;

public class RefValueAdapter1 implements RefValueAdapter {
    CovidReferenceValues1API api;

    public RefValueAdapter1() {
        this.api = new CovidReferenceValues1API();
    }



    @Override
    public RefValue getRefValue(String param) {
        return new RefValue(getMinValue(param),getMaxValue(param),getMetric(param));
    }

    @Override
    public double getMinValue(String param) {
        return this.api.getMinReferenceValue(param, 12345);
    }

    @Override
    public double getMaxValue(String param) {
        return this.api.getMaxReferenceValue(param, 12345);
    }

    @Override
    public String getMetric(String param) {
        return this.api.usedMetric(param, 12345);
    }

}
