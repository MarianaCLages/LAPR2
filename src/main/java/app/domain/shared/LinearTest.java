package app.domain.shared;

public class LinearTest {
    public static void main(String[] args) {
        double[] x = {825, 215, 1070, 550, 480, 920, 1350, 325, 670, 1215};
        double[] y = {3.5, 1, 4, 2, 1, 3, 4.5, 1.5, 3, 5};
        LinearRegression linearRegression = new LinearRegression(x, y);
        System.out.println(linearRegression.toString());
        int i = 1;
        for (double xi : x) {
            System.out.println(String.format("x%d: %.4f", i, linearRegression.predict(xi)));
        }
    }
}
