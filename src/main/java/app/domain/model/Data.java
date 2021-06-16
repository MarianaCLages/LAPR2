package app.domain.model;

import app.domain.shared.exceptions.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Period;

public class Data {

    private String historicalDays;
    private int date;
    private int confidenceLevelIC;
    private String significanceLevel;
    private LocalDate intervalStartDate;
    private LocalDate intervalEndDate;
    private boolean dayReport;
    private boolean weekReport;
    private boolean monthlyReport;

    public Data(String historicalDays, int date, int confidenceLevelIC, String significanceLevel) {

        this.historicalDays = historicalDays;
        this.date = date;
        this.confidenceLevelIC = confidenceLevelIC;
        this.significanceLevel = significanceLevel;

    }

    public Data() {

        this.historicalDays = null;
        this.date = 0;
        this.confidenceLevelIC = 0;
        this.significanceLevel = null;
        this.intervalStartDate = null;
        this.intervalEndDate = null;
        this.dayReport = false;
        this.weekReport = false;
        this.monthlyReport = false;

    }

    public void setConfidenceLevelIC(int confidenceLevelIC) throws ConfidenceLevelInvalidException, ConfidenceLevelICEmptyException {
        checkConfidenceLevelIC(confidenceLevelIC);
        this.confidenceLevelIC = confidenceLevelIC;
    }

    public void setHistoricalDays(String historicalDays) throws HistoricalDaysEmptyException, HistoricalDaysInvalidException {
        checkHistoricaldays(historicalDays);
        this.historicalDays = historicalDays;
    }

    public void setIntervalDates(int date) throws DateEmptyException, DateInvalidException {
        checkIntervalDates(date);
        this.date = date;
    }

    public void setDates(LocalDate start, LocalDate end) {
        this.intervalStartDate = start;
        this.intervalEndDate = end;
    }

    private void checkConfidenceLevelIC(int confidenceLevelIC) throws ConfidenceLevelInvalidException {

        if (confidenceLevelIC > 100 || confidenceLevelIC < 0) {
            throw new ConfidenceLevelInvalidException();
        }

    }

    private void checkIntervalDates(long date) throws DateEmptyException, DateInvalidException {

        if (date == 0) {
            throw new DateEmptyException();
        }

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

    public int getConfidenceLevel() {
        return confidenceLevelIC;
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

        return Period.between(this.intervalStartDate, this.intervalEndDate).getDays();

    }

    public void setWeekReport(boolean weekReport) {
        this.weekReport = weekReport;
    }

    public void setDayReport(boolean dayReport) {
        this.dayReport = dayReport;
    }

    public boolean getDayReportValue() {
        return this.dayReport;
    }

    public boolean getWeekReportValue() {
        return this.weekReport;
    }

    public boolean getMontlhyReportValue() {
        return this.monthlyReport;
    }

    public void setMonthlyReport(boolean monthlyReport) {
        this.monthlyReport = monthlyReport;
    }

}

