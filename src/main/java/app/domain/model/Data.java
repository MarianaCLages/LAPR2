package app.domain.model;

import app.controller.App;
import app.domain.shared.exceptions.*;
import app.ui.gui.controllers.SceneController;
import org.apache.commons.lang3.StringUtils;

public class Data {

    private String historicalDays;
    private int date;
    private String confidenceLevelIC;
    private String significanceLevel;

    public Data(String historicalDays, int date, String confidenceLevelIC, String significanceLevel) {

        this.historicalDays = historicalDays;
        this.date = date;
        this.confidenceLevelIC = confidenceLevelIC;
        this.significanceLevel = significanceLevel;

    }

    public Data() {

        this.historicalDays = null;
        this.date = 0;
        this.confidenceLevelIC = null;
        this.significanceLevel = null;

    }

    public void setConfidenceLevelIC(String confidenceLevelIC) throws ConfidenceLevelInvalidException, ConfidenceLevelICEmptyException {
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

    private void checkConfidenceLevelIC(String confidenceLevelIC) throws ConfidenceLevelInvalidException, ConfidenceLevelICEmptyException {

        if (confidenceLevelIC.isEmpty() || StringUtils.isBlank(confidenceLevelIC)) {
            throw new ConfidenceLevelICEmptyException();
        }

        int n = Integer.parseInt(confidenceLevelIC);

        if (n > 100 || n < 0) {
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

    public int getDate() {
        return date;
    }

    public String getConfidenceLevelIC() {
        return confidenceLevelIC;
    }

    public String getHistoricalDays() {
        return historicalDays;
    }


}

