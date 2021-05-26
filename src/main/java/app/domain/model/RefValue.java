package app.domain.model;

public class RefValue {
    private double minValue;
    private double maxValue;
    private String metric;

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

    @Override
    public String toString() {
        return "RefValue{" +
                "minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", metric='" + metric + '\'' +
                '}';
    }
}
