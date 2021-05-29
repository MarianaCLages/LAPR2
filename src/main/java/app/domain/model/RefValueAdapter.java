package app.domain.model;

public interface RefValueAdapter {

    /**
     * @param param string that represents the parameter
     * @return instance of reference value associated with parameter provided
     */
    RefValue getRefValue(String param);

    /**
     * @param param string that represents the parameter
     * @return minimum reference value associated with parameter provided
     */
    double getMinValue(String param);

    /**
     * @param param string that represents the parameter
     * @return maximum reference value associated with parameter provided
     */
    double getMaxValue(String param);

    /**
     * @param param string that represents the parameter
     * @return string that represents the metric associated with parameter provided
     */
    String getMetric(String param);

}
