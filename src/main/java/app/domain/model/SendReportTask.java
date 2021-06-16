package app.domain.model;


import app.controller.App;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.exceptions.InvalidLengthException;

import java.io.Serializable;
import java.util.Properties;
import java.util.TimerTask;

public class SendReportTask extends TimerTask implements Serializable {
    public SendReportTask() {
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        Properties prop = App.getProperties();
        double confidenceLevelAnova = Double.parseDouble(prop.getProperty("significance.level.anova"));
        double confidenceLevelVariables = Double.parseDouble(prop.getProperty("significance.level.coefficient"));
        double confidenceLevelEstimated = Double.parseDouble(prop.getProperty("significance.level.estimated"));


        double[] m1 = {27, 58, 86, 120, 140, 152, 169, 218, 226, 258};
        double[] m2 = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

        LinearRegression l = new LinearRegression(m1, m2);
        System.out.println("Linear");
        System.out.println(l);
        System.out.println("r = " + l.getR());

        System.out.println("Hypothesis tests for regression coefficients");
        System.out.println("HO:b=0 (a=0) H1: b<>0 (a<>0)");
        System.out.println("Tb: " + l.getTestStatisticb());
        System.out.println("T: " + l.getCriticValueStudent(confidenceLevelVariables));
        if (l.getTestStatisticb() > l.getCriticValueStudent(confidenceLevelVariables)) {
            System.out.println("H0 rejected\n");
        } else {
            System.out.println("H0 not rejected\n");
        }

        System.out.println("HO:a=0 H1: a<>0");
        System.out.println("Tb: " + l.getTestStatistica());
        System.out.println("T: " + l.getCriticValueStudent(confidenceLevelVariables));
        if (l.getTestStatistica() > l.getCriticValueStudent(confidenceLevelVariables)) {
            System.out.println("H0 rejected\n");
        } else {
            System.out.println("H0 not rejected\n");
        }

        System.out.println("ANOVA ");
        System.out.println("F0: " + l.getF0());
        System.out.println("F1: " + l.getCriticValueFisher(confidenceLevelAnova));

        if (l.getF0() > l.getCriticValueFisher(confidenceLevelAnova)) {
            System.out.println("Passa no teste");
        } else {
            System.out.println("Não passou no test");
        }

        System.out.println("\n\n-------------------------------------------------------------------");

        System.out.println("Multilinear \n");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        double[][] matrix1 = {
                {120, 19},
                {200, 8},
                {150, 12},
                {180, 15},
                {240, 16},
                {250, 13}
        };
        double[] matrixb = {23.8, 24.2, 22.0, 26.2, 33.5, 35};


        MultiLinearRegression s = new MultiLinearRegression(matrix1, matrixb);

        System.out.println();
        System.out.println("Lower x1: " + s.lowerLimitCoeficient(1, 0.05));

        System.out.println("Upper x1: " + s.upperLimitCoeficient(1, 0.05));
        System.out.println();
        System.out.println("Lower x2: " + s.lowerLimitCoeficient(2, 0.05));

        System.out.println("Upper x2: " + s.upperLimitCoeficient(2, 0.05));
        System.out.println();

        double[] arr1 = {170, 12};

        try {
            System.out.println("Estimativa: " + s.getEstimate(arr1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        try {
            System.out.println("Limite Inferior: " + s.lowerLimitAnswer(arr1, 0.05));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Limite Superior: " + s.upperLimitAnswer(arr1, 0.05));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n\n\n ANOVA teste \n ");
        System.out.println("F0: " + s.getF0());
        System.out.println("F1: " + s.getCriticValueFisher(confidenceLevelAnova));

        if (s.getF0() > s.getCriticValueFisher(confidenceLevelAnova)) {
            System.out.println("Passa no teste");
        } else {
            System.out.println("Não passou no test");
        }


        System.out.println("\n\n\nTestes de coefficients \n ");

        for (int i = 0; i < matrix1[0].length + 1; i++) {
            System.out.println("x" + i);
            System.out.println("HO:b=0 H1: b<>0 \n");
            System.out.println("T0 =" + s.getTestStatistics(i));
            System.out.println("T =" + s.getCriticValueStudent(confidenceLevelVariables));

            if (s.getTestStatistics(i) > s.getCriticValueStudent(confidenceLevelVariables)) {
                System.out.println("H0 rejected\n");
            } else {
                System.out.println("H0 not rejected\n");
            }
        }

        int i = 0;

        for (double y : matrixb) {
            System.out.println("n=" + (i + 1));
            System.out.println("y = " + y);
            try {
                System.out.println("^y= " + s.getEstimate(matrix1[i]));
            } catch (InvalidLengthException e) {
                e.printStackTrace();
            }
            System.out.println("interval: ");
            try {
                System.out.println("]" + s.lowerLimitAnswer(matrix1[i], confidenceLevelEstimated) + ";" + s.upperLimitAnswer(matrix1[i], confidenceLevelEstimated) + "[");

            } catch (InvalidLengthException e) {
                e.printStackTrace();
            }
            i++;
            System.out.println();
        }

    }
}
