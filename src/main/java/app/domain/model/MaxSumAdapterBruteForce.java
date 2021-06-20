package app.domain.model;

import app.domain.shared.BruteForceMaxSum;

public class MaxSumAdapterBruteForce implements MaxSumAdapter {

    /**
     * Gets the max sum of a array.
     *
     * @param array the array
     * @return the max sum of the array
     */
    @Override
    public int[] getMaxSum(int[] array) {
        return BruteForceMaxSum.max(array);
    }
}
