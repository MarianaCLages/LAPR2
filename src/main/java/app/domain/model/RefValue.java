package app.domain.model;

import java.io.Serializable;

/**
 * Class that represents the reference value of a given parameter
 */
public class RefValue implements Serializable {
    private final double minValue;
    private final double maxValue;
    private final String metric;

    /**
     * Constructor of the reference value class
     *
     * @param minValue minimum value of the parameter
     * @param maxValue maximum value of the parameter
     * @param metric   metric of the parameter
     */
    public RefValue(double minValue, double maxValue, String metric) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.metric = metric;
    }

    /**
     * @return minimum value of the parameter
     */
    public double getMinValue() {
        return minValue;
    }

    /**
     * @return maximum value of the parameter
     */
    public double getMaxValue() {
        return maxValue;
    }

    /**
     * @return metric of the parameter
     */
    public String getMetric() {
        return metric;
    }


}
