package app.domain.shared;

import app.domain.shared.exceptions.InvalidLengthException;
import org.apache.commons.math3.distribution.TDistribution;

import java.util.Arrays;


public class MultiLinearRegression {
    double[][] C;
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
    private double alpha;
    private double[][] x;
    private double[] y;
    private int n;
    private int k;


    public MultiLinearRegression(double[][] x, double[] y, double alfa) {

        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
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
        this.alpha = alfa;
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
        System.out.println("x: " + Arrays.deepToString(x));
        System.out.println("xt: " + Arrays.deepToString(transpose(this.x)));
        System.out.println("xtx: " + Arrays.deepToString(matrixProduct(transpose(this.x), this.x)));
        System.out.println("xtx-1: " + Arrays.deepToString(inverse(matrixProduct(transpose(this.x), this.x))));
        this.C = inverse(matrixProduct(transpose(this.x), this.x));
        System.out.println("C:  " + Arrays.deepToString(inverse(matrixProduct(transpose(this.x), this.x))));

        this.intercept = this.betta[0];
        this.slope1 = this.betta[1];
        this.slope2 = this.betta[2];
        System.out.println("y= " + this.slope1 + "x1 + " + this.slope2 + "x2 " + " + " + this.intercept);
        System.out.println();

        this.SQt = calculateSQT(y);
        System.out.println("SQt: " + SQt);
        this.SQr = calculateSQR(y, betta, this.x);
        System.out.println("SQr: " + SQr);
        this.SQe = calculateSQE(this.SQt, this.SQr);
        System.out.println("SQe: " + SQe);

        this.r2 = SQr / SQt;
        System.out.println("r2 = " + this.r2);


        this.r2Ajusted = calculateR2Adjusted(this.r2, n, k);
        System.out.println("r2 adjusted = " + r2Ajusted);

        this.MQr = SQr / k;

        this.MQe = SQe / (n - (k + 1));

    }

    public static void main(String[] args) throws InvalidLengthException {

        double[][] matrix1 = {
                {4, 36},
                {5, 33},
                {5.5, 37},
                {7, 37},
                {6, 34},
                {5, 32},
                {7, 36},
                {8, 35},
                {8.5, 38},
                {9, 39},

        };
        double[] matrixb = {4, 4.5, 5, 6.5, 7, 7.8, 7.5, 8, 8, 8.5};
        MultiLinearRegression s = new MultiLinearRegression(matrix1, matrixb, 0.05);
        System.out.println(s.lowerLimitCoeficient(1));

        System.out.println(s.upperLimitCoeficient(1));

        double[] arr = {5.5, 36};
        System.out.println("Estimativa: " + s.getEstimate(arr));
        System.out.println("Limite Inferior: " + s.lowerLimitAnswer(arr));
        System.out.println("\n");
        System.out.println("Limite Superior: " + s.upperLimitAnswer(arr));


    }


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

    private static double[][] transpose(double[][] matrix) {
        double[][] transpose = new double[matrix[0].length][matrix.length];

//Code to transpose a matrix
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transpose[i][j] = matrix[j][i];
            }
        }
        return transpose;
    }

    //dar aqui os creditos
    //uses the laplace theorem to calculate the determinant
    private static double determinant(double[][] matrix) {
        if (matrix.length != matrix[0].length)
            throw new IllegalStateException("matrix should be a square matrizx");

        if (matrix.length == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < matrix[0].length; i++)
            det += Math.pow(-1, i) * matrix[0][i]
                    * determinant(minor(matrix, 0, i));
        return det;
    }

    //calculates the inverse matix using the
    private static double[][] inverse(double[][] matrix) {
        double[][] inverse = new double[matrix.length][matrix.length];

        // minors and cofactors
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                inverse[i][j] = Math.pow(-1, i + j)
                        * determinant(minor(matrix, i, j));

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

    public double lowerLimitCoeficient(int index) {
        double[][] C = inverse(matrixProduct(transpose(this.x), this.x));
        TDistribution td = new TDistribution(this.n - this.k - 1);

        double critTD = td.inverseCumulativeProbability(1 - this.alpha);

        double variance = this.MQe;

        return this.betta[index] - critTD * Math.sqrt(variance * C[index][index]);

    }

    public double upperLimitCoeficient(int index) {
        TDistribution td = new TDistribution(this.n - (this.k + 1));

        double critTD = td.inverseCumulativeProbability(1 - this.alpha);

        double variance = this.MQe;

        return this.betta[index] + critTD * Math.sqrt(variance * this.C[index][index]);

    }

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

    private double calculateym(double[] y) {
        double ym = 0;

        for (int i = 0; i < y.length; i++) {
            ym += y[i];
        }
        return ym / y.length;

    }

    private double calculateSQT(double[] y) {
        int n = y.length;
        double yty = 0;
        for (int i = 0; i < y.length; i++) {
            yty += y[i] * y[i];
        }

        return yty - n * Math.pow(calculateym(y), 2);
    }

    private double calculateSQE(double SQT, double SQR) {
        return SQT - SQR;
    }

    private double calculateR2Adjusted(double r2, double n, double k) {

        return (1 - (((n - 1) / (n - (k + 1))) * (1 - r2)));
    }

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

    public double lowerLimitAnswer(double[] x0) throws InvalidLengthException {
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

        System.out.println(Arrays.toString(x1));
        System.out.println("C: " + Arrays.deepToString(this.C));
//esta a dar mal ver isto
        double[] cx0 = matrixVectorProduct(this.C, x1);

        System.out.println("Cx0 = " + Arrays.toString(cx0));
        System.out.println("x1t = " + Arrays.deepToString(x1t));

        double xtcx = 0;

        for (int i = 0; i < cx0.length; i++) {
            xtcx += cx0[i] * x1t[i][0];
        }

        System.out.println("XtCX0= " + xtcx);

        TDistribution td = new TDistribution(this.n - this.k - 1);

        double critTD = td.inverseCumulativeProbability(1 - this.alpha/2);
        System.out.println("critTD: " + critTD);
        double variance = this.MQe;


        return getEstimate(x0) - critTD * Math.sqrt(variance * (1 + xtcx));


    }

    public double upperLimitAnswer(double[] x0) throws InvalidLengthException {
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

        System.out.println(Arrays.toString(x1));
        System.out.println("C: " + Arrays.deepToString(this.C));
//esta a dar mal ver isto
        double[] cx0 = matrixVectorProduct(this.C, x1);

        System.out.println("Cx0 = " + Arrays.toString(cx0));
        System.out.println("x1t = " + Arrays.deepToString(x1t));

        double xtcx = 0;

        for (int i = 0; i < cx0.length; i++) {
            xtcx += cx0[i] * x1t[i][0];
        }

        System.out.println("XtCX0= " + xtcx);

        TDistribution td = new TDistribution(this.n - this.k - 1);

        double critTD = td.inverseCumulativeProbability(1 - this.alpha/2);
        System.out.println("critTD: " + critTD);
        double variance = this.MQe;


        return getEstimate(x0) + critTD * Math.sqrt(variance * (1 + xtcx));


    }

}
