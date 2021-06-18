package app.domain.shared;

public interface Regression {
    double getCriticValueStudent(double alpha);
    double getCriticValueFisher(double alphaf);
    double getR2();
    double getR();
}
