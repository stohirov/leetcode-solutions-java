import java.util.Arrays;

public class Solution943 {

  public String shortestSuperstring(String[] words) {
    int n = words.length;
    int[][] overlap = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j) {
          continue;
        }
        overlap[i][j] = computeOverlap(words[i], words[j]);
      }
    }

    int[][] dp = new int[1 << n][n];
    int[][] parent = new int[1 << n][n];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    for (int[] row : parent) {
      Arrays.fill(row, -1);
    }
    for (int i = 0; i < n; i++) {
      dp[1 << i][i] = 0;
    }
    for (int mask = 1; mask < (1 << n); mask++) {
      for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) == 0) {
          continue;
        }
        int prevMask = mask ^ (1 << i);
        if (prevMask == 0) {
          continue;
        }
        for (int j = 0; j < n; j++) {
          if ((prevMask & (1 << j)) == 0 || dp[prevMask][j] < 0) {
            continue;
          }
          int val = dp[prevMask][j] + overlap[j][i];
          if (val > dp[mask][i]) {
            dp[mask][i] = val;
            parent[mask][i] = j;
          }
        }
      }
    }
    int full = (1 << n) - 1;
    int best = 0;
    for (int i = 1; i < n; i++) {
      if (dp[full][i] > dp[full][best]) {
        best = i;
      }
    }

    int[] order = new int[n];
    int idx = n - 1;
    int mask = full;
    int cur = best;
    while (cur != -1) {
      order[idx--] = cur;
      int p = parent[mask][cur];
      mask ^= (1 << cur);
      cur = p;
    }
    StringBuilder sb = new StringBuilder(words[order[0]]);
    for (int k = 1; k < n; k++) {
      int ov = overlap[order[k - 1]][order[k]];
      sb.append(words[order[k]].substring(ov));
    }
    return sb.toString();
  }

  private int computeOverlap(String a, String b) {
    int max = Math.min(a.length(), b.length());
    for (int len = max; len > 0; len--) {
      if (a.regionMatches(a.length() - len, b, 0, len)) {
        return len;
      }
    }
    return 0;
  }
}
