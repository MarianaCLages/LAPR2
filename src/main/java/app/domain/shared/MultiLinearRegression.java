package app.domain.shared;

import java.util.Arrays;

public class MultiLinearRegression {
    private double intercept;
    private double slope;
    private double r2;
    private double svar0;
    private double svar1;


    public MultiLinearRegression(double[][] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }

        // number of observations
        int n = y.length;


    }

    public static void main(String[] args) {
        double[][] matrix = {
                {1, 4, 3},
                {1, 2, 4},
                {1, 0, 3}
        };
        double [] matrixb = {1,5,6};
        System.out.println(Arrays.deepToString(matrix));

        System.out.println(Arrays.deepToString(matrix));

        System.out.println(Arrays.deepToString(inverse(matrix)));

        System.out.println(Arrays.deepToString(transpose(matrix)));
        double[] betta = matrixVectorProduct(matrixProduct(inverse(matrixProduct(transpose(matrix),matrix)),transpose(matrix)),matrixb);
        System.out.println(Arrays.toString(betta));
    }

    public static double[] matrixVectorProduct(double[][] matrix, double[] vector) {


        double[] produto = new double[matrix.length];
        int i=0;


        for(int j = 0; j<matrix.length; j++){

            for(int k = 0; k < matrix.length; k++){

                produto[i] += matrix[j][k] * vector[k];

            }

            i++;

        }


        return produto;

    }

    public static double[][] matrixProduct(double[][] matrixA, double[][] matrixB) {

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
        double[][] transpose = new double[matrix.length][matrix[0].length];  //3 rows and 3 columns

//Code to transpose a matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
}
