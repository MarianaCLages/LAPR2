package app.domain.model;

import app.domain.shared.BruteForceMaxSum;

public class MaxSumAdapterBruteForce implements MaxSumAdapter {
    @Override
    public int[] getMaxSum(int[] array) {
        return BruteForceMaxSum.max(array);
    }
}
