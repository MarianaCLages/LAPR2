package app.domain.model;

import app.domain.shared.exceptions.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Data implements Serializable {

    private String historicalDays;
    private double confidenceLevelAnova;
    private double confidenceLevelVariables;
    private double confidenceLevelEstimated;
    private LocalDate intervalStartDate;
    private LocalDate intervalEndDate;
    private String selection;
    private int date;

    public Data() {

        this.historicalDays = null;
        this.date = 0;
        this.confidenceLevelAnova = 0;
        this.intervalStartDate = null;
        this.intervalEndDate = null;
        this.selection = null;
    }

    public void setConfidenceLevelAnova(double confidenceLevelAnova) throws ConfidenceLevelInvalidException {
        checkConfidenceLevelIC(confidenceLevelAnova);
        this.confidenceLevelAnova = confidenceLevelAnova;
    }

    public void setConfidenceLevelEstimated(double confidenceLevelEstimated) throws ConfidenceLevelInvalidException {
        checkConfidenceLevelIC(confidenceLevelEstimated);
        this.confidenceLevelEstimated = confidenceLevelEstimated;
    }

    public void setConfidenceLevelVariables(double confidenceLevelVariables) throws ConfidenceLevelInvalidException {
        checkConfidenceLevelIC(confidenceLevelVariables);
        this.confidenceLevelVariables = confidenceLevelVariables;
    }

    public void setHistoricalDays(String historicalDays) throws HistoricalDaysEmptyException, HistoricalDaysInvalidException {
        checkHistoricaldays(historicalDays);
        this.historicalDays = historicalDays;
    }

    public void setIntervalDates(int date) throws DateInvalidException {
        checkIntervalDates(date);
        this.date = date;
    }

    public void setDates(LocalDate start, LocalDate end) {
        this.intervalStartDate = start;
        this.intervalEndDate = end;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getSelection() {
        return this.selection;
    }

    private void checkConfidenceLevelIC(double confidenceLevelIC) throws ConfidenceLevelInvalidException {

        if (confidenceLevelIC > 100 || confidenceLevelIC < 0) {
            throw new ConfidenceLevelInvalidException();
        }

    }

    private void checkIntervalDates(long date) throws DateInvalidException {

        if (date < 0) {
            throw new DateInvalidException();
        }

    }

    private void checkHistoricaldays(String historicalDays) throws HistoricalDaysEmptyException, HistoricalDaysInvalidException {

        if (historicalDays.isEmpty() || StringUtils.isBlank(historicalDays)) {
            throw new HistoricalDaysEmptyException();
        }

        int n = Integer.parseInt(historicalDays);

        if (n > 365) {
            throw new HistoricalDaysInvalidException();
        }

    }

    public int getHistoricalDaysInt() {
        return Integer.parseInt(historicalDays);
    }

    public double getConfidenceLevelAnova() {
        return (confidenceLevelAnova / 100);
    }

    public double getConfidenceLevelEstimated() {
        return (confidenceLevelEstimated / 100);
    }

    public double getConfidenceLevelVariables() {
        return (confidenceLevelVariables / 100);
    }

    public String getHistoricalDays() {
        return historicalDays;
    }

    public LocalDate getIntervalEndDate() {
        return intervalEndDate;
    }

    public LocalDate getIntervalStartDate() {
        return intervalStartDate;
    }

    public int getDifferenceInDates() {

        return (int) ChronoUnit.DAYS.between(this.intervalStartDate, this.intervalEndDate);

    }

}

