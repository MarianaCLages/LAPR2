package app.domain.model;

public class RefValue {
    private final double minValue;
    private final double maxValue;
    private final String metric;

    public RefValue(double minValue, double maxValue, String metric) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.metric = metric;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public String getMetric() {
        return metric;
    }


}
