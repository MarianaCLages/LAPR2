package app.controller;

import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CheckPerformanceController {
    private final Company company;
    private final TestStore tStore;
    private final ClientStore cStore;

    public CheckPerformanceController() {
        this(App.getInstance().getCompany());
    }

    public CheckPerformanceController(Company company) {
        this.company = company;
        this.tStore = company.getTestList();
        this.cStore = company.getClientList();
    }

    public int[] getSubArray(LocalDate beg, LocalDate end) {
        Test[] tests = new Test[tStore.getTestsInsideDateInterval(beg, end).length];
        var i=0;
        for (Object o : tStore.getTestsInsideDateInterval(beg, end)) {
            tests[i] = (Test) o;
            i++;
        }

        System.out.println(Arrays.toString(tests));
        System.out.println(tests.length);

        return null;
    }


}
