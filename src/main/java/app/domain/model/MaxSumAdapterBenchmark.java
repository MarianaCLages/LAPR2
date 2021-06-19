package app.domain.model;

import com.isep.mdis.Sum;

public class MaxSumAdapterBenchmark implements MaxSumAdapter {

    /**
     * Gets the max sum of a array.
     *
     * @param array the array
     * @return the max sum of the array
     */
    @Override
    public int[] getMaxSum(int[] array) {
        return Sum.Max(array);
    }
}
