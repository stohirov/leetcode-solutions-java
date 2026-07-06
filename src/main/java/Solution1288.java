import java.util.Arrays;

public class Solution1288 {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);

        int remaining = 0;
        int prevEnd = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[1] > prevEnd) {
                remaining++;
                prevEnd = interval[1];
            }
        }
        return remaining;
    }
}
