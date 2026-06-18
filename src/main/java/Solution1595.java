import java.util.Arrays;
import java.util.List;

public class Solution1595 {
  public int connectTwoGroups(List<List<Integer>> cost) {
    int n = cost.size();
    int m = cost.getFirst().size();
    int[] minRight = new int[m];
    Arrays.fill(minRight, Integer.MAX_VALUE);
    for (int j = 0; j < m; j++) {
      for (int i = 0; i < n; i++) {
        minRight[j] = Math.min(minRight[j], cost.get(i).get(j));
      }
    }
    int[][] dp = new int[n + 1][1 << m];
    for (int[] row : dp) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }
    dp[0][0] = 0;
    for (int i = 0; i < n; i++) {
      for (int mask = 0; mask < (1 << m); mask++) {
        if (dp[i][mask] == Integer.MAX_VALUE) {
          continue;
        }
        for (int j = 0; j < m; j++) {
          int newMask = mask | (1 << j);
          int newCost = dp[i][mask] + cost.get(i).get(j);
          if (newCost < dp[i + 1][newMask]) {
            dp[i + 1][newMask] = newCost;
          }
        }
      }
    }
    int result = Integer.MAX_VALUE;
    for (int mask = 0; mask < (1 << m); mask++) {
      if (dp[n][mask] == Integer.MAX_VALUE) {
        continue;
      }
      int extra = 0;
      for (int j = 0; j < m; j++) {
        if ((mask & (1 << j)) == 0) {
          extra += minRight[j];
        }
      }
      result = Math.min(result, dp[n][mask] + extra);
    }
    return result;
  }
}
