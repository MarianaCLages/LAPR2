package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxSumAdapterBruteForceTest {

    @Test
    public void getMaxSum(){
        int[] input = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int[] expected = {4, -1, 2, 1};
        MaxSumAdapterBruteForce sumAdapterBruteForce = new MaxSumAdapterBruteForce();

        Assert.assertArrayEquals(expected,sumAdapterBruteForce.getMaxSum(input));

    }

}