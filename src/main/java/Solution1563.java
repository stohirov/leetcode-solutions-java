public class Solution1563 {
  public int stoneGameV(int[] stoneValue) {
    int n = stoneValue.length;
    int[] prefix = new int[n + 1];
    for (int i = 0; i < n; i++) {
      prefix[i + 1] = prefix[i] + stoneValue[i];
    }

    int[][] dp = new int[n][n];
    int[][] bestLeft = new int[n][n];
    int[][] bestRight = new int[n][n];
    for (int i = 0; i < n; i++) {
      bestLeft[i][i] = stoneValue[i];
      bestRight[i][i] = stoneValue[i];
    }

    for (int i = n - 1; i >= 0; i--) {
      int mid = i;
      for (int j = i + 1; j < n; j++) {
        int total = prefix[j + 1] - prefix[i];
        while (2 * (prefix[mid + 1] - prefix[i]) < total) {
          mid++;
        }
        int leftSum = prefix[mid + 1] - prefix[i];

        int best = 0;
        if (mid - 1 >= i) {
          best = Math.max(best, bestLeft[i][mid - 1]);
        }
        if (mid <= j - 1) {
          best = Math.max(best, bestRight[mid + 1][j]);
        }
        if (mid < j && 2 * leftSum == total) {
          best = Math.max(best, leftSum + dp[i][mid]);
        }

        dp[i][j] = best;
        bestLeft[i][j] = Math.max(bestLeft[i][j - 1], total + dp[i][j]);
        bestRight[i][j] = Math.max(bestRight[i + 1][j], total + dp[i][j]);
      }
    }

    return dp[0][n - 1];
  }
}
