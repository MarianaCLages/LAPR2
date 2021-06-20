package app.domain.model;

import org.junit.Assert;
import org.junit.Test;


public class MaxSumAdapterBenchmarkTest {
    @Test
    public void getMaxSum(){
        int[] input = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int[] expected = {4, -1, 2, 1};
        MaxSumAdapterBenchmark sumAdapterBenchmark = new MaxSumAdapterBenchmark();

        Assert.assertArrayEquals(expected,sumAdapterBenchmark.getMaxSum(input));

    }

}