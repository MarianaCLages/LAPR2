package app.domain.model;

import app.domain.shared.Constants;
import com.example3.CovidReferenceValues1API;

import java.io.Serializable;

public class RefValueAdapter1 implements RefValueAdapter, Serializable {
    CovidReferenceValues1API api;

    /**
     * Constructor of the class, it creates a new object of the externalAPI
     */
    public RefValueAdapter1() {
        this.api = new CovidReferenceValues1API();
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
        return this.api.getMinReferenceValue(param, Constants.ACCESS_KEY);
    }

    /**
     * @param param string that represents the parameter
     * @return maximum reference value associated with parameter provided
     */
    @Override
    public double getMaxValue(String param) {
        return this.api.getMaxReferenceValue(param, Constants.ACCESS_KEY);
    }

    /**
     * @param param string that represents the parameter
     * @return string that represents the metric associated with parameter provided
     */
    @Override
    public String getMetric(String param) {
        return this.api.usedMetric(param, Constants.ACCESS_KEY);
    }

}
