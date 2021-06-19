package app.domain.shared;

import app.domain.shared.exceptions.InvalidLengthException;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

public class MultiLinearRegression implements Regression {
    private double[][] C;
    private double F0;
    private double intercept;
    private double slope1;
    private double slope2;
    private double r2;
    private double r2Ajusted;
    private double[] betta;
    private double SQt;
    private double SQr;
    private double SQe;
    private double MQr;
    private double MQe;
    private double[][] x;
    private double[] y;
    private int n;
    private int k;

    /**
     * Does the multi linear regression.
     *
     * @param x the x (matrix)
     * @param y the y (array)
     */
    public MultiLinearRegression(double[][] x, double[] y) {

        if (x.length != y.length) {
            throw new IllegalArgumentException("The array's length are not equal!");
        }

        double[][] m1 = new double[x.length][x[0].length + 1];
        for (int i = 0; i < m1.length; i++) {
            m1[i][0] = 1;
            for (int j = 1; j < m1[i].length; j++) {
                m1[i][j] = x[i][j - 1];
            }
        }

        this.k = 2;
        this.n = y.length;
        this.x = m1;
        this.y = y;

        double[][] xt = transpose(this.x);

        double[][] xtx = matrixProduct(xt, this.x);

        double[][] xtxinv = inverse(xtx);

        double[][] xtxinvxt = matrixProduct(xtxinv, transpose(this.x));

        this.betta = new double[xtxinvxt.length];

        for (int j = 0; j < xtxinvxt.length; j++) {
            for (int m = 0; m < xtxinvxt[0].length; m++) {

                betta[j] += xtxinvxt[j][m] * y[m];

            }
        }

        this.C = inverse(matrixProduct(transpose(this.x), this.x));

        this.intercept = this.betta[0];
        this.slope1 = this.betta[1];
        this.slope2 = this.betta[2];


        this.SQt = calculateSQT(y);
        this.SQr = calculateSQR(y, betta, this.x);
        this.SQe = calculateSQE(this.SQt, this.SQr);

        this.r2 = SQr / SQt;


        this.r2Ajusted = calculateR2Adjusted(this.r2, n, k);

        this.MQr = SQr / this.k;


        this.MQe = this.SQe / (this.n - (this.k + 1));

        this.F0 = this.MQr / this.MQe;

    }

    /**
     * Calculates the product of a matrix and a vector.
     *
     * @param matrix the matrix
     * @param vector the vector
     * @return the matrix resulting from the multiplication
     */
    private static double[] matrixVectorProduct(double[][] matrix, double[] vector) {


        double[] product = new double[matrix.length];
        int i = 0;

        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix.length; k++) {
                product[i] += matrix[j][k] * vector[k];
            }

            i++;

        }
        return product;
    }

    /**
     * Calculates the product of two matrices.
     *
     * @param matrixA the matrix A
     * @param matrixB the matrix B
     * @return the matrix resulting from the multiplication
     */
    private static double[][] matrixProduct(double[][] matrixA, double[][] matrixB) {

        double[][] product = new double[matrixA.length][matrixB[0].length];

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    product[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return product;
    }

    /**
     * Calculates the transposed matrix.
     *
     * @param matrix the matrix
     * @return the transposed matrix
     */
    private static double[][] transpose(double[][] matrix) {
        double[][] transpose = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transpose[i][j] = matrix[j][i];
            }
        }
        return transpose;
    }

    /**
     * Calculates the determinant of a matrix.
     *
     * @param matrix the matrix
     * @return the matrix's determinant
     */
    //dar aqui os creditos
    //uses the laplace theorem to calculate the determinant
    private static double determinant(double[][] matrix) {
        if (matrix.length != matrix[0].length)
            throw new IllegalStateException("The matrix should be a square matrix!");

        if (matrix.length == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < matrix[0].length; i++)
            det += Math.pow(-1, i) * matrix[0][i]
                    * determinant(minor(matrix, 0, i));
        return det;
    }

    /**
     * Calculates the inverse matrix.
     *
     * @param matrix the matrix
     * @return the inverse matrix of a given matrix
     */
    //calculates the inverse matrix using the complement matrix
    private static double[][] inverse(double[][] matrix) {
        double[][] inverse = new double[matrix.length][matrix.length];

        // minors and cofactors
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                inverse[i][j] = Math.pow(-1, i + j) * determinant(minor(matrix, i, j));

        // adjugate and determinant
        double det = 1.0 / determinant(matrix);
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = inverse[i][j];
                inverse[i][j] = inverse[j][i] * det;
                inverse[j][i] = temp * det;
            }
        }

        return inverse;
    }

    private static double[][] minor(double[][] matrix, int row, int column) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
        return minor;
    }

    /**
     * Gets the interception.
     *
     * @return the interception
     */
    public double getIntercept() {
        return intercept;
    }

    /**
     * Gets the slope 1.
     *
     * @return the slope 1
     */
    public double getSlope1() {
        return slope1;
    }

    /**
     * Gets the slope 2.
     *
     * @return the slope 2
     */
    public double getSlope2() {
        return slope2;
    }

    /**
     * Gets the F0.
     *
     * @return the F0
     */
    public double getF0() {
        return F0;
    }

    /**
     * Gets the critic value (t-Student).
     *
     * @param alpha the alpha
     * @return the critic value (t-Student)
     */
    public double getCriticValueStudent(double alpha) {
        TDistribution td = new TDistribution(this.n - this.k - 1);

        return td.inverseCumulativeProbability(1 - alpha);
    }

    /**
     * Gets the critic value (f-Snedecor).
     *
     * @param alphaf the alpha
     * @return the critic value (f-Snedecor)
     */
    public double getCriticValueFisher(double alphaf) {
        FDistribution fDistribution = new FDistribution(this.k, this.n - (this.k + 1));
        return fDistribution.inverseCumulativeProbability(1 - alphaf);
    }

    /**
     * Calculates the lower limit coefficient.
     *
     * @param index the index
     * @param alpha the alpha
     * @return the lower limit coefficient
     */
    public double lowerLimitCoefficient(int index, double alpha) {

        double critTD = getCriticValueStudent(alpha);

        double variance = this.MQe;

        return this.betta[index] - critTD * Math.sqrt(variance * this.C[index][index]);

    }

    /**
     * Calculates the upper limit coefficient.
     *
     * @param index the index
     * @param alpha the alpha
     * @return the upper limit coefficient
     */
    public double upperLimitCoefficient(int index, double alpha) {

        double critTD = getCriticValueStudent(alpha);

        double variance = this.MQe;

        return this.betta[index] + critTD * Math.sqrt(variance * this.C[index][index]);

    }

    /**
     * Calculates the SQr.
     *
     * @param y     the y (array)
     * @param betta the betta
     * @param x     the x (matrix)
     * @return the SQr
     */
    private double calculateSQR(double[] y, double[] betta, double[][] x) {
        double[][] bettat = new double[betta.length][1];
        for (int i = 0; i < betta.length; i++) {
            bettat[i][0] = betta[i];
        }
        x = transpose(x);


        double[] btxt = new double[x[0].length];
        for (int i = 0; i < x[0].length; i++) {
            for (int j = 0; j < bettat.length; j++) {
                btxt[i] += x[j][i] * bettat[j][0];
            }
        }

        double btxty = 0;
        for (int i = 0; i < y.length; i++) {
            btxty += y[i] * btxt[i];
        }

        return btxty - y.length * Math.pow(calculateym(y), 2);

    }

    /**
     * Calculates the ym.
     *
     * @param y the y (array)
     * @return the ym
     */
    private double calculateym(double[] y) {
        double ym = 0;

        for (int i = 0; i < y.length; i++) {
            ym += y[i];
        }
        return ym / y.length;

    }

    /**
     * Calculates the SQt.
     *
     * @param y the y (array)
     * @return the SQt
     */
    private double calculateSQT(double[] y) {
        int n = y.length;
        double yty = 0;
        for (int i = 0; i < y.length; i++) {
            yty += y[i] * y[i];
        }

        return yty - n * Math.pow(calculateym(y), 2);
    }

    /**
     * Calculates the SQe.
     *
     * @param SQT the SQt
     * @param SQR the SQr
     * @return the SQe
     */
    private double calculateSQE(double SQT, double SQR) {
        return SQT - SQR;
    }

    /**
     * Calculates the adjusted r2.
     *
     * @param r2 the r2
     * @param n  the n
     * @param k  the k
     * @return the adjusted r2
     */
    private double calculateR2Adjusted(double r2, double n, double k) {

        return (1 - (((n - 1) / (n - (k + 1))) * (1 - r2)));
    }

    /**
     * Gets the estimated y.
     *
     * @param x the x (array)
     * @return the estimated y
     * @throws InvalidLengthException
     */
    public double getEstimate(double[] x) throws InvalidLengthException {
        if (x.length != this.betta.length - 1) {
            throw new InvalidLengthException();
        }

        double yEstimated = 0;

        yEstimated += yEstimated + this.betta[0];
        for (int i = 0; i < x.length; i++) {
            yEstimated += this.betta[i + 1] * x[i];
        }

        return yEstimated;
    }

    /**
     * Calculates the lower limit answer.
     *
     * @param x0    the x0 (array)
     * @param alpha the alpha
     * @return the lower limit answer
     * @throws InvalidLengthException
     */
    public double lowerLimitAnswer(double[] x0, double alpha) throws InvalidLengthException {
        if (x0.length != this.betta.length - 1) {
            throw new InvalidLengthException();
        }

        double[] x1 = new double[x0.length + 1];

        x1[0] = 1;

        for (int i = 1; i < x1.length; i++) {
            x1[i] = x0[i - 1];
        }

        double[][] x1t = new double[x1.length][1];

        for (int i = 0; i < x1.length; i++) {
            x1t[i][0] = x1[i];
        }

        double[] cx0 = matrixVectorProduct(this.C, x1);

        double xtcx = 0;

        for (int i = 0; i < cx0.length; i++) {
            xtcx += cx0[i] * x1t[i][0];
        }


        double critTD = getCriticValueStudent(alpha);
        double variance = this.MQe;

        return getEstimate(x0) - critTD * Math.sqrt(variance * (1 + xtcx));

    }

    /**
     * Calculates the upper limit answer.
     *
     * @param x0    the x0 (array)
     * @param alpha the alpha
     * @return the upper limit answer
     * @throws InvalidLengthException
     */
    public double upperLimitAnswer(double[] x0, double alpha) throws InvalidLengthException {
        if (x0.length != this.betta.length - 1) {
            throw new InvalidLengthException();
        }

        double[] x1 = new double[x0.length + 1];

        x1[0] = 1;

        for (int i = 1; i < x1.length; i++) {
            x1[i] = x0[i - 1];
        }

        double[][] x1t = new double[x1.length][1];

        for (int i = 0; i < x1.length; i++) {
            x1t[i][0] = x1[i];
        }

        double[] cx0 = matrixVectorProduct(this.C, x1);

        double xtcx = 0;

        for (int i = 0; i < cx0.length; i++) {
            xtcx += cx0[i] * x1t[i][0];
        }

        double critTD = getCriticValueStudent(alpha);
        double variance = this.MQe;

        return getEstimate(x0) + critTD * Math.sqrt(variance * (1 + xtcx));

    }

    /**
     * Gets the test statistics.
     *
     * @param index the index
     * @return the test statistics
     */
    public double getTestStatistics(int index) {
        return this.betta[index] / Math.sqrt(this.MQe * this.C[index][index]);
    }

    /**
     * Gets the r2.
     *
     * @return the r2
     */
    public double getR2() {
        return r2;
    }

    /**
     * Gets the r by making the square root of r2.
     *
     * @return the r
     */
    @Override
    public double getR() {
        return Math.sqrt(this.r2);
    }

    /**
     * Gets the adjusted r2.
     *
     * @return the adjusted r2
     */
    public double getR2Adjusted() {
        return r2Ajusted;
    }

    /**
     * Gets the SQt.
     *
     * @return the SQt
     */
    public double getSQt() {
        return SQt;
    }

    /**
     * Gets the SQr.
     *
     * @return the SQr
     */
    public double getSQr() {
        return SQr;
    }

    /**
     * Gets the SQe.
     *
     * @return the SQe
     */
    public double getSQe() {
        return SQe;
    }

    /**
     * Gets the MQr.
     *
     * @return the MQr
     */
    public double getMQr() {
        return MQr;
    }

    /**
     * Gets the MQe.
     *
     * @return the MQe
     */
    public double getMQe() {
        return MQe;
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
     * Gets the k.
     *
     * @return the k
     */
    public int getK() {
        return k;
    }

    /**
     * Returns the textual description of the multi linear regression in the format: slope1, slope2 and interception.
     *
     * @return the multi linear regression results/details
     */
    @Override
    public String toString() {
        return slope1 + "x1 + " + slope2 + "x2 + " + intercept;
    }
}
