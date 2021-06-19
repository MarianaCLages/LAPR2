package app.domain.model;

import com.example2.EMRefValue;
import com.example2.ExternalModule2API;

import java.io.Serializable;


public class RefValueAdapter2 implements RefValueAdapter, Serializable {
    ExternalModule2API api;

    /**
     * Constructor of the class, it creates a new object of the externalAPI
     */
    public RefValueAdapter2() {
        this.api = new ExternalModule2API();
    }

    /**
     * @param param string that represents the parameter
     * @return instance of reference value associated with parameter provided
     */
    @Override
    public RefValue getRefValue(String param) {
        return new RefValue(getMinValue(param), getMaxValue(param), getMetric(param));
    }

    /**
     * @param param string that represents the parameter
     * @return minimum reference value associated with parameter provided
     */
    @Override
    public double getMinValue(String param) {
        EMRefValue reference = api.getReferenceFor(param);

        return reference.getMinValue();
    }

    /**
     * @param param string that represents the parameter
     * @return maximum reference value associated with parameter provided
     */
    @Override
    public double getMaxValue(String param) {
        EMRefValue reference = api.getReferenceFor(param);

        return reference.getMaxValue();
    }

    /**
     * @param param string that represents the parameter
     * @return string that represents the metric associated with parameter provided
     */
    @Override
    public String getMetric(String param) {
        EMRefValue reference = api.getReferenceFor(param);

        return reference.getMetric();
    }
}
