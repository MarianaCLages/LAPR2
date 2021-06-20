package app.domain.shared;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

/**
 * The code LinearRegression class performs a simple linear regression
 * on an set of n data points (y_i, x_i).
 * That is, it fits a straight line y = alpha + beta * x,
 * (where y is the response variable, x is the predictor variable,
 * alpha is the y-intercept, and beta is the slope)
 * that minimizes the sum of squared residuals of the linear regression model.
 * It also computes associated statistics, including the coefficient of
 * determination R^2 and the standard deviation of the
 * estimates for the slope and y-intercept.
 */
public class LinearRegression implements Regression {
    private final double intercept;
    private final double slope;


    private final double r2;
    private final double xbar;
    private final double ybar;


    private final int n;
    private final double st;
    private final double mse;
    private final double msr;
    private final double f0;
    private double sxx;
    private double syy;
    private double sxy;
    private double sr;
    private double se;

    /**
     * Performs a linear regression on the data points (y[i], x[i]).
     *
     * @param x the values of the predictor variable
     * @param y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public LinearRegression(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        this.n = x.length;

        // first pass
        double sumx = 0.0;
        double sumy = 0.0;
        double sumx2 = 0.0;
        for (int i = 0; i < n; i++) {
            sumx += x[i];
            sumx2 += x[i] * x[i];
            sumy += y[i];
        }
        this.xbar = sumx / n;
        this.ybar = sumy / n;

        // second pass: compute summary statistics
        this.sxx = 0.0;
        this.syy = 0.0;
        this.sxy = 0.0;
        for (int i = 0; i < n; i++) {
            this.sxx += (x[i] - this.xbar) * (x[i] - this.xbar);
            this.syy += (y[i] - this.ybar) * (y[i] - this.ybar);
            this.sxy += (x[i] - this.xbar) * (y[i] - this.ybar);
        }
        this.slope = sxy / sxx;
        this.intercept = this.ybar - this.slope * this.xbar;

        // more statistical analysis
        this.se = 0.0;      // residual sum of squares
        this.sr = 0.0;      // regression sum of squares
        for (int i = 0; i < n; i++) {
            double fit = this.slope * x[i] + this.intercept;
            this.se += (fit - y[i]) * (fit - y[i]);
            sr += (fit - this.ybar) * (fit - this.ybar);
        }
        this.st = this.se + this.sr;

        int degreesOfFreedom = n - 2;
        this.r2 = sr / syy;
        this.mse = se / degreesOfFreedom;
        this.msr = this.sr;

        this.f0 = msr / mse;

    }

    /**
     * Returns the y-intercept alpha of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the y-intercept alpha of the best-fit line y = alpha + beta * x
     */
    public double intercept() {
        return intercept;
    }

    /**
     * Returns the slope beta of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the slope beta of the best-fit line y = alpha + beta * x
     */
    public double slope() {
        return slope;
    }

    /**
     * Returns the coefficient of determination R^2.
     *
     * @return the coefficient of determination R^2,
     * which is a real number between 0 and 1
     */
    public double R2() {
        return r2;
    }

    /**
     * Gets the r by making the square root of R^2.
     *
     * @return the R
     */
    public double R() {
        return Math.sqrt(r2);
    }

    /**
     * Returns the expected response y given the value of the predictor
     * variable x.
     *
     * @param x the value of the predictor variable
     * @return the expected response y given the value of the predictor
     * variable x
     */
    public double predict(double x) {
        return slope * x + intercept;
    }

    /**
     * Gets the critic value (t-Student).
     *
     * @param alpha the alpha
     * @return the critic value (t-Student)
     */
    public double getCriticValueStudent(double alpha) {
        TDistribution td = new TDistribution(this.n);

        return td.inverseCumulativeProbability(1 - alpha);
    }

    /**
     * Gets the critic value (f-Snedecor).
     *
     * @param alphaf the alpha
     * @return the critic value (f-Snedecor)
     */
    public double getCriticValueFisher(double alphaf) {
        FDistribution fDistribution = new FDistribution(1, this.n - 2);
        return fDistribution.inverseCumulativeProbability(1 - alphaf);
    }

    /**
     * Calculates the lower limit answer.
     *
     * @param x     the x
     * @param alpha the alpha
     * @return the lower limit answer
     */
    public double lowerLimitAnswer(double x, double alpha) {
        double y = predict(x);
        double criticalValue = getCriticValueStudent(alpha);
        double s = Math.sqrt(this.se / (this.n - 2));
        return y - criticalValue * s * Math.sqrt(1 + 1 / n + (Math.pow((x - this.xbar), 2) / this.sxx));
    }

    /**
     * Calculates the upper limit answer.
     *
     * @param x     the x
     * @param alpha the alpha
     * @return the upper limit answer
     */
    public double upperLimitAnswer(double x, double alpha) {
        double y = predict(x);
        double s = Math.sqrt(this.se / (this.n - 2));
        double criticalValue = getCriticValueStudent(alpha);
        return y + criticalValue * s * Math.sqrt(1 + 1 / n + (Math.pow((x - this.xbar), 2) / this.sxx));
    }

    /**
     * Calculates the upper limit for the param a.
     *
     * @param alpha the alpha
     * @return the upper limit for the param a
     */
    public double upperLimitParama(double alpha) {
        double s = Math.sqrt(this.se / (this.n - 2));
        double criticalValue = getCriticValueStudent(alpha);
        return this.intercept + criticalValue * s * Math.sqrt(1 / n + (Math.pow(this.xbar, 2) / this.sxx));
    }

    /**
     * Calculates the lower limit for the param a.
     *
     * @param alpha the alpha
     * @return the lower limit for the param a
     */
    public double lowerLimitParama(double alpha) {
        double s = Math.sqrt(this.se / (this.n - 2));
        double criticalValue = getCriticValueStudent(alpha);
        return this.intercept - criticalValue * s * Math.sqrt(1 / n + (Math.pow(this.xbar, 2) / this.sxx));
    }

    /**
     * Calculates the upper limit for the param b.
     *
     * @param alpha the alpha
     * @return the upper limit for the param b
     */
    public double upperLimitParamb(double alpha) {
        double s = Math.sqrt(this.se / (this.n - 2));
        double criticalValue = getCriticValueStudent(alpha);
        return this.slope + criticalValue * s * Math.sqrt(1 / this.sxx);
    }

    /**
     * Calculates the lower limit for the param b.
     *
     * @param alpha the alpha
     * @return the lower limit for the param b
     */
    public double lowerLimitParamb(double alpha) {
        double s = Math.sqrt(this.se / (this.n - 2));
        double criticalValue = getCriticValueStudent(alpha);
        return this.slope - criticalValue * s * Math.sqrt(1 / this.sxx);
    }

    /**
     * Gets the test statistics for the param b
     *
     * @return the test statistics for the param b
     */
    public double getTestStatisticb() {
        return (this.slope) / (Math.sqrt(this.se / (this.n - 2)) / Math.sqrt(this.sxx));
    }

    /**
     * Gets the test statistics for the param a
     *
     * @return the test statistics for the param a
     */
    public double getTestStatistica() {
        return (this.intercept) / (Math.sqrt(this.se / (this.n - 2)) / Math.sqrt((1 / n) + Math.pow(this.xbar, 2) / this.sxx));
    }

    /**
     * Gets the R^2.
     *
     * @return the R^2
     */
    public double getR2() {
        return r2;
    }

    /**
     * Gets the ST.
     *
     * @return the ST
     */
    public double getSt() {
        return st;
    }

    /**
     * Gets the MSE.
     *
     * @return the MSE
     */
    public double getMse() {
        return mse;
    }

    /**
     * Gets the MSR.
     *
     * @return the MSR
     */
    public double getMsr() {
        return msr;
    }

    /**
     * Gets the f0.
     *
     * @return the f0
     */
    public double getF0() {
        return f0;
    }

    /**
     * Gets the SR.
     *
     * @return the SR
     */
    public double getSr() {
        return sr;
    }

    /**
     * Gets the SE.
     *
     * @return the SE
     */
    public double getSe() {
        return se;
    }

    /**
     * Gets the R by making the square root of R^2.
     *
     * @return the R
     */
    public double getR() {
        return Math.sqrt(this.r2);
    }

    /**
     * Gets the n.
     *
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * Returns a string representation of the simple linear regression model.
     *
     * @return a string representation of the simple linear regression model,
     * including the best-fit line and the coefficient of determination
     * R^2
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%.4f n + %.4f", slope(), intercept()));

        return s.toString();
    }
}


