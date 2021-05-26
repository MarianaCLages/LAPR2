package app.domain.model;

public interface RefValueAdapter {
    RefValue getRefValue(String param);

    double getMinValue(String param);

    double getMaxValue(String param);

    String getMetric(String param);

}
