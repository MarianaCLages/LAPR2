package app.domain.model;

import com.example2.EMRefValue;
import com.example2.ExternalModule2API;


public class RefValueAdapter2 implements RefValueAdapter {
    ExternalModule2API api;

    public RefValueAdapter2() {
        this.api = new ExternalModule2API();
    }

    @Override
    public RefValue getRefValue(String param) {
        return new RefValue(getMinValue(param), getMaxValue(param), getMetric(param));
    }

    @Override
    public double getMinValue(String param) {
        EMRefValue reference = api.getReferenceFor(param);

        return reference.getMinValue();
    }

    @Override
    public double getMaxValue(String param) {
        EMRefValue reference = api.getReferenceFor(param);

        return reference.getMaxValue();
    }

    @Override
    public String getMetric(String param) {
        EMRefValue reference = api.getReferenceFor(param);

        return reference.getMetric();
    }
}
