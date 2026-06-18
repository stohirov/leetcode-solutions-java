import java.util.Arrays;

public class Solution1494 {

  public int minNumberOfSemesters(int n, int[][] relations, int k) {
    int[] prereq = new int[n];
    for (int[] r : relations) {
      prereq[r[1] - 1] |= (1 << (r[0] - 1));
    }

    int full = (1 << n) - 1;
    int[] dp = new int[1 << n];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int taken = 0; taken <= full; taken++) {
      if (dp[taken] == Integer.MAX_VALUE) {
        continue;
      }

      int available = 0;
      for (int c = 0; c < n; c++) {
        if ((taken & (1 << c)) == 0 && (taken & prereq[c]) == prereq[c]) {
          available |= (1 << c);
        }
      }

      for (int sub = available; sub > 0; sub = (sub - 1) & available) {
        if (Integer.bitCount(sub) <= k) {
          int next = taken | sub;
          if (dp[taken] + 1 < dp[next]) {
            dp[next] = dp[taken] + 1;
          }
        }
      }
    }
    return dp[full];
  }
}
