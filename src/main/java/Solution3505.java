import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class Solution3505 {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = windowCosts(nums, x);
        long inf = Long.MAX_VALUE / 2;

        long[] prev = new long[n + 1];
        for (int j = 1; j <= k; j++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, inf);
            for (int i = j * x; i <= n; i++) {
                cur[i] = Math.min(cur[i - 1], prev[i - x] + cost[i - x]);
            }
            prev = cur;
        }

        return prev[n];
    }

    private long[] windowCosts(int[] nums, int x) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        Comparator<Integer> byValue = Comparator.<Integer>comparingInt(i -> nums[i]).thenComparingInt(i -> i);
        TreeSet<Integer> low = new TreeSet<>(byValue);
        TreeSet<Integer> high = new TreeSet<>(byValue);
        long sumLow = 0, sumHigh = 0;

        for (int i = 0; i < n; i++) {
            low.add(i);
            sumLow += nums[i];

            int moved = low.pollLast();
            sumLow -= nums[moved];
            high.add(moved);
            sumHigh += nums[moved];

            if (i >= x) {
                int out = i - x;
                if (low.remove(out)) {
                    sumLow -= nums[out];
                } else {
                    high.remove(out);
                    sumHigh -= nums[out];
                }
            }

            while (high.size() > low.size()) {
                int up = high.pollFirst();
                sumHigh -= nums[up];
                low.add(up);
                sumLow += nums[up];
            }
            while (low.size() > high.size() + 1) {
                int down = low.pollLast();
                sumLow -= nums[down];
                high.add(down);
                sumHigh += nums[down];
            }

            if (i >= x - 1) {
                long median = nums[low.last()];
                cost[i - x + 1] = median * low.size() - sumLow + sumHigh - median * high.size();
            }
        }

        return cost;
    }
}
