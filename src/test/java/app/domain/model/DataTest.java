package app.domain.model;

import app.domain.shared.exceptions.ConfidenceLevelInvalidException;
import app.domain.shared.exceptions.DateInvalidException;
import app.domain.shared.exceptions.HistoricalDaysEmptyException;
import app.domain.shared.exceptions.HistoricalDaysInvalidException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DataTest {

    Data data = new Data();
    Calendar calendar = Calendar.getInstance();


    @Test
    public void setConfidenceLevelAnova() throws ConfidenceLevelInvalidException {
        data.setConfidenceLevelAnova(10);
    }

    @Test
    public void setConfidenceLevelEstimated() throws ConfidenceLevelInvalidException {
        data.setConfidenceLevelEstimated(10);
    }

    @Test
    public void setConfidenceLevelVariables() throws ConfidenceLevelInvalidException {
        data.setConfidenceLevelVariables(10);
    }

    @Test
    public void setHistoricalDays() throws HistoricalDaysInvalidException, HistoricalDaysEmptyException {
        data.setHistoricalDays("10");
    }

    @Test
    public void setIntervalDates() throws DateInvalidException {
        data.setIntervalDates(10);
    }

    @Test
    public void setDates() {
        Date date1 = calendar.getTime();
        LocalDate beginDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        data.setDates(beginDate, LocalDate.now());
    }

    @Test
    public void setSelection() {

        data.setSelection("Day");
    }

    @Test
    public void getSelection() {
        data.setSelection("Day");
        Assert.assertNotNull(data.getSelection());
    }

    @Test
    public void getHistoricalDaysInt() throws HistoricalDaysInvalidException, HistoricalDaysEmptyException {
        data.setHistoricalDays("10");
        Assert.assertNotNull(data.getHistoricalDaysInt());
    }

    @Test
    public void getConfidenceLevelAnova() {
        Assert.assertNotNull(data.getConfidenceLevelAnova());

    }

    @Test
    public void getConfidenceLevelEstimated() {
        Assert.assertNotNull(data.getConfidenceLevelEstimated());
    }

    @Test
    public void getConfidenceLevelVariables() {
        Assert.assertNotNull(data.getConfidenceLevelVariables());
    }

    @Test
    public void getHistoricalDays() throws HistoricalDaysInvalidException, HistoricalDaysEmptyException {
        data.setHistoricalDays("10");
        Assert.assertNotNull(data.getHistoricalDays());
    }

    @Test(expected = HistoricalDaysInvalidException.class)
    public void getHistoricalDaysInvalid() throws HistoricalDaysInvalidException, HistoricalDaysEmptyException {
        data.setHistoricalDays("400");
        Assert.assertNotNull(data.getHistoricalDays());
    }

    @Test(expected = HistoricalDaysEmptyException.class)
    public void getHistoricalDaysEmpty() throws HistoricalDaysInvalidException, HistoricalDaysEmptyException {
        data.setHistoricalDays("");
        Assert.assertNotNull(data.getHistoricalDays());
    }

    @Test
    public void getIntervalEndDate() {
        Date date1 = calendar.getTime();
        LocalDate beginDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        data.setDates(beginDate,LocalDate.now());
        Assert.assertNotNull(data.getIntervalEndDate());
    }

    @Test
    public void getIntervalStartDate() {
        Date date1 = calendar.getTime();
        LocalDate beginDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        data.setDates(beginDate,LocalDate.now());
        Assert.assertNotNull(data.getIntervalStartDate());
    }

    @Test
    public void getDifferenceInDates() throws DateInvalidException {
        Date date1 = calendar.getTime();
        LocalDate beginDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        data.setDates(beginDate,LocalDate.now());
        Assert.assertNotNull(data.getDifferenceInDates());
    }

    @Test(expected = DateInvalidException.class)
    public void getDatesInvalid() throws DateInvalidException {
       data.setIntervalDates(-1);
    }

    @Test(expected = ConfidenceLevelInvalidException.class)
    public void invalidConfidenceLevel() throws ConfidenceLevelInvalidException {
        data.setConfidenceLevelAnova(200);
    }

}