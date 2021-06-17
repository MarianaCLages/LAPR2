package app.domain.model;


import app.controller.App;
import app.domain.shared.LinearRegression;
import app.domain.shared.MultiLinearRegression;
import app.domain.shared.exceptions.InvalidLengthException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Properties;
import java.util.TimerTask;

public class SendReportTask extends TimerTask implements Serializable {
    private LocalDate finishDate;
    private int historicalDays;
    private LocalDate beginningDate;
    private double confidenceLevelAnova;
    private double confidenceLevelVariables;
    private double confidenceLevelEstimated;
    private String independentVariable;

    public SendReportTask() {
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {

        getProps();

        double[] m1 = {27, 58, 86, 120, 140, 152, 169, 218, 226, 258};
        double[] m2 = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

        LinearRegression l = new LinearRegression(m1, m2);

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

    private void getProps() {
        Properties prop = App.getProperties();
        this.beginningDate = LocalDate.parse(prop.getProperty("fit.date.beginning"));
        this.finishDate = LocalDate.parse(prop.getProperty("fit.date.ending"));
        this.historicalDays = Integer.parseInt((prop.getProperty("historical.days")));
        this.confidenceLevelAnova = Double.parseDouble(prop.getProperty("significance.level.anova"));
        this.confidenceLevelVariables = Double.parseDouble(prop.getProperty("significance.level.coefficient"));
        this.confidenceLevelEstimated = Double.parseDouble(prop.getProperty("significance.level.estimated"));
    }

}



