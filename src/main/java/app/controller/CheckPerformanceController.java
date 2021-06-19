package app.controller;

import app.domain.model.Company;
import app.domain.model.MaxSumAdapter;
import app.domain.model.Test;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static app.domain.shared.Constants.BENCHMARK_ALGORITHM_PATH;
import static app.domain.shared.Constants.BRUTEFORCE_ALGORITHM_PATH;

public class CheckPerformanceController {
    private final Company company;
    private final TestStore tStore;
    private final ClientStore cStore;
    LocalDateTime[] times;
    int[] differenceArray;


    public CheckPerformanceController() {
        this(App.getInstance().getCompany());
    }

    public CheckPerformanceController(Company company) {
        this.company = company;
        this.tStore = company.getTestList();
        this.cStore = company.getClientList();
    }

    public MaxSumAdapter getAdapter(String algorithm) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        MaxSumAdapter adapter;
        if (algorithm.equals("Benchmark Algorithm")) {
            Class<?> oClass = Class.forName(BENCHMARK_ALGORITHM_PATH);
            adapter = (MaxSumAdapter) oClass.newInstance();
        } else {
            Class<?> oClass = Class.forName(BRUTEFORCE_ALGORITHM_PATH);
            adapter = (MaxSumAdapter) oClass.newInstance();
        }

        return adapter;

    }


    public int[] getSubArray(LocalDate beg, LocalDate end, String algorithm) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MaxSumAdapter adapter = getAdapter(algorithm);

        Test[] tests = new Test[tStore.getTestsInsideDateInterval(beg, end).length];
        int i = 0;
        for (Object o : tStore.getTestsInsideDateInterval(beg, end)) {
            tests[i] = (Test) o;
            i++;
        }
        int numberOfDays = (int) ChronoUnit.DAYS.between(beg, end);
        int numberOfPeriods = numberOfDays * 24;

        times = new LocalDateTime[numberOfPeriods];

        differenceArray = new int[numberOfPeriods];

        int j = 0;


        while (beg.isBefore(end)) {

            LocalDateTime time1 = beg.atTime(8, 0);
            LocalDateTime time3 = beg.atTime(20, 0);

            LocalDateTime time2 = beg.atTime(8, 30);


            while (time2.isBefore(time3)) {
                int created = 0;
                int validated = 0;

                for (Test t : tests) {
                    if (t.getCreatedDate().isAfter(time1) && t.getCreatedDate().isBefore(time2)) {
                        created++;
                    }
                    if (t.getValidatedDate().isAfter(time1) && t.getValidatedDate().isBefore(time2)) {
                        validated++;
                    }

                }

                differenceArray[j] = created - validated;
                times[j] = time1;

                time1 = time1.plusMinutes(30);

                time2 = time1.plusMinutes(30);
                j++;

            }

            beg = beg.plusDays(1);

        }


        int[] subarray = adapter.getMaxSum(differenceArray);


        return subarray;
    }

    public LocalDateTime[] getDates(int[] subarray) {
        int[] begEnd = getBeginEnd(differenceArray, subarray);
        LocalDateTime[] dates = new LocalDateTime[2];

        dates[0] = times[begEnd[0]];
        dates[1] = times[begEnd[1]];

        return dates;
    }

    private int[] getBeginEnd(int[] sequence, int[] subSeq) {
        int i;
        int k = 0;
        int[] indexes = new int[subSeq.length];
        for (i = 0; i < sequence.length; i++) {
            while (sequence[i] == subSeq[k]) {
                indexes[k] = i;
                i++;
                k++;
                if (k == subSeq.length) {
                    return indexes;
                }
                if (sequence[i] != subSeq[k]) {
                    k = 0;
                }
            }
        }
        for (i = 0; i < indexes.length; i++) {
            indexes[i] = 0;
        }

        int[] begEnd = new int[2];
        begEnd[0] = indexes[0];
        begEnd[1] = indexes[indexes.length - 1];

        return begEnd;
    }

    public int numberClients() {
        return cStore.getClientList().size();
    }

    //nome do método para teres os testes getAllTestsInAInterval


    public void setInformation(String selection){
        System.out.println(selection);
        String option = selection;
    }

    public int numberWaitingResults() {

        return tStore.getWaitingResult().size();
    }

    public int numberWaitingDiagnosis() {

        return tStore.getWaitingDiagnosis().size();
    }
}





