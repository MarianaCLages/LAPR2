package app.domain.shared;

import java.util.Arrays;

public class BruteForceMaxSum {
    private BruteForceMaxSum() {
    }

    /**
     * Gets the maximum sum sub-array
     * @param array the array
     * @return the maximum sum sub-array
     */
    public static int[] max(int[] array) {
        int max = Integer.MIN_VALUE;
        int beggining = 0;
        int ending = 0;
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if (sum > max) {
                    max = sum;
                    beggining = i;
                    ending = j;
                }
            }
        }
        return Arrays.copyOfRange(array, beggining, ending + 1);
    }
}
