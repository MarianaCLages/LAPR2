package app.domain.model;

import com.example1.ExternalModule3API;

public class RefValueAdapter3 implements RefValueAdapter {
    ExternalModule3API api;


    public RefValueAdapter3() {
        this.api = new ExternalModule3API();
    }

    @Override
    public RefValue getRefValue(String param) {
        return new RefValue(getMinValue(param),getMaxValue(param),getMetric(param));
    }

    @Override
    public double getMinValue(String param) {
        return api.getMinReferenceValue(param, 12345);
    }

    @Override
    public double getMaxValue(String param) {
        return api.getMaxReferenceValue(param, 12345);
    }

    @Override
    public String getMetric(String param) {
        return api.usedMetric(param, 12345);
    }
}
