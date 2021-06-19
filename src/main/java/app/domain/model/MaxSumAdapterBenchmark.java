package app.domain.model;

import com.isep.mdis.Sum;

public class MaxSumAdapterBenchmark implements MaxSumAdapter {
    @Override
    public int[] getMaxSum(int[] array) {
        return Sum.Max(array);
    }
}
