import java.util.Arrays;

public class Solution1066 {
  public int assignBikes(int[][] workers, int[][] bikes) {
    int n = workers.length;
    int m = bikes.length;
    int full = 1 << m;
    // dp[mask] = min total distance to assign first popcount(mask) workers
    // using the bikes selected in mask
    int[] dp = new int[full];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    int best = Integer.MAX_VALUE;
    for (int mask = 0; mask < full; mask++) {
      if (dp[mask] == Integer.MAX_VALUE) {
        continue;
      }
      int worker = Integer.bitCount(mask);
      if (worker == n) {
        best = Math.min(best, dp[mask]);
        continue;
      }
      for (int b = 0; b < m; b++) {
        if ((mask & (1 << b)) != 0) {
          continue;
        }
        int next = mask | (1 << b);
        int dist = Math.abs(workers[worker][0] - bikes[b][0])
            + Math.abs(workers[worker][1] - bikes[b][1]);
        if (dp[mask] + dist < dp[next]) {
          dp[next] = dp[mask] + dist;
        }
      }
    }
    return best;
  }
}
