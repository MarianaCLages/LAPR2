package app.domain.model;


import app.controller.App;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;

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
        System.out.println(l.lowerLimitParama(0.025));
        System.out.println(l.upperLimitParama(0.025));

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

    }
}
