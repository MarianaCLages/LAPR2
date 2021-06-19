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

    /**
     * Constructor.
     */
    public Data() {

        this.historicalDays = null;
        this.date = 0;
        this.confidenceLevelAnova = 0;
        this.intervalStartDate = null;
        this.intervalEndDate = null;
        this.selection = null;
    }

    /**
     * Sets the ANOVA's confidence level.
     *
     * @param confidenceLevelAnova the ANOVA's confidence level
     * @throws ConfidenceLevelInvalidException
     */
    public void setConfidenceLevelAnova(double confidenceLevelAnova) throws ConfidenceLevelInvalidException {
        checkConfidenceLevelIC(confidenceLevelAnova);
        this.confidenceLevelAnova = confidenceLevelAnova;
    }

    /**
     * Sets the confidence level estimated.
     *
     * @param confidenceLevelEstimated the confidence level estimated
     * @throws ConfidenceLevelInvalidException
     */
    public void setConfidenceLevelEstimated(double confidenceLevelEstimated) throws ConfidenceLevelInvalidException {
        checkConfidenceLevelIC(confidenceLevelEstimated);
        this.confidenceLevelEstimated = confidenceLevelEstimated;
    }

    /**
     * Sets the confidence level variables.
     *
     * @param confidenceLevelVariables the confidence level variables
     * @throws ConfidenceLevelInvalidException
     */
    public void setConfidenceLevelVariables(double confidenceLevelVariables) throws ConfidenceLevelInvalidException {
        checkConfidenceLevelIC(confidenceLevelVariables);
        this.confidenceLevelVariables = confidenceLevelVariables;
    }

    /**
     * Sets the historical days.
     *
     * @param historicalDays the historical days
     * @throws HistoricalDaysEmptyException
     * @throws HistoricalDaysInvalidException
     */
    public void setHistoricalDays(String historicalDays) throws HistoricalDaysEmptyException, HistoricalDaysInvalidException {
        checkHistoricaldays(historicalDays);
        this.historicalDays = historicalDays;
    }

    /**
     * Sets the date intervals.
     *
     * @param date the date
     * @throws DateInvalidException
     */
    public void setIntervalDates(int date) throws DateInvalidException {
        checkIntervalDates(date);
        this.date = date;
    }

    /**
     * Sets the dates.
     *
     * @param start the start date
     * @param end   the end date
     */
    public void setDates(LocalDate start, LocalDate end) {
        this.intervalStartDate = start;
        this.intervalEndDate = end;
    }

    /**
     * Sets the selected report generation time.
     *
     * @param selection the selected report generation time
     */
    public void setSelection(String selection) {
        this.selection = selection;
    }

    /**
     * Gets the selected report generation time.
     *
     * @return the selected report generation time
     */
    public String getSelection() {
        return this.selection;
    }

    /**
     * Checks if the confidence level is valid.
     *
     * @param confidenceLevelIC the confidence level
     * @throws ConfidenceLevelInvalidException
     */
    private void checkConfidenceLevelIC(double confidenceLevelIC) throws ConfidenceLevelInvalidException {

        if (confidenceLevelIC > 100 || confidenceLevelIC < 0) {
            throw new ConfidenceLevelInvalidException();
        }

    }

    /**
     * Checks if the date intervals are valid.
     *
     * @param date the date
     * @throws DateInvalidException
     */
    private void checkIntervalDates(long date) throws DateInvalidException {

        if (date < 0) {
            throw new DateInvalidException();
        }

    }

    /**
     * Checks if the historical days are valid.
     *
     * @param historicalDays the historical days
     * @throws HistoricalDaysEmptyException
     * @throws HistoricalDaysInvalidException
     */
    private void checkHistoricaldays(String historicalDays) throws HistoricalDaysEmptyException, HistoricalDaysInvalidException {

        if (historicalDays.isEmpty() || StringUtils.isBlank(historicalDays)) {
            throw new HistoricalDaysEmptyException();
        }

        int n = Integer.parseInt(historicalDays);

        if (n > 365) {
            throw new HistoricalDaysInvalidException();
        }

    }

    /**
     * Gets the number of historical days.
     *
     * @return the number of historical days
     */
    public int getHistoricalDaysInt() {
        return Integer.parseInt(historicalDays);
    }

    /**
     * Gets the ANOVA's confidence level.
     *
     * @return the ANOVA's confidence level
     */
    public double getConfidenceLevelAnova() {
        return (confidenceLevelAnova / 100);
    }

    /**
     * Gets the estimated confidence level.
     *
     * @return the estimated confidence level
     */
    public double getConfidenceLevelEstimated() {
        return (confidenceLevelEstimated / 100);
    }

    /**
     * Gets the confidence levels variables.
     *
     * @return the confidence levels variables
     */
    public double getConfidenceLevelVariables() {
        return (confidenceLevelVariables / 100);
    }

    /**
     * Gets the historical days.
     *
     * @return the historical days
     */
    public String getHistoricalDays() {
        return historicalDays;
    }

    /**
     * Gets the end date (interval).
     * @return the end date
     */
    public LocalDate getIntervalEndDate() {
        return intervalEndDate;
    }

    /**
     * Gets the start date (interval).
     * @return the start date
     */
    public LocalDate getIntervalStartDate() {
        return intervalStartDate;
    }

    /**
     * Gets the difference between dates.
     * @return the difference between the start and end dates
     */
    public int getDifferenceInDates() {
        return (int) ChronoUnit.DAYS.between(this.intervalStartDate, this.intervalEndDate);
    }

}

