package app.domain.shared;

public interface Regression {

    /**
     * Gets the critic value (t-Student).
     *
     * @param alpha the alpha
     * @return the critic value (t-Student)
     */
    double getCriticValueStudent(double alpha);

    /**
     * Gets the critic value (f-Snedecor).
     *
     * @param alphaf the alpha
     * @return the critic value (f-Snedecor)
     */
    double getCriticValueFisher(double alphaf);

    /**
     * Gets the R^2.
     *
     * @return the R^2
     */
    double getR2();

    /**
     * Gets the R by making the square root of R^2.
     *
     * @return the R
     */
    double getR();
}
