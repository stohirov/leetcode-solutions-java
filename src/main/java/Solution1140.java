public class Solution1140 {

  public int stoneGameII(int[] piles) {
    int n = piles.length;

    int[] suffix = new int[n + 1];
    for (int i = n - 1; i >= 0; i--) {
      suffix[i] = suffix[i + 1] + piles[i];
    }

    int[][] best = new int[n + 1][n + 1];
    for (int i = n - 1; i >= 0; i--) {
      for (int m = n; m >= 1; m--) {
        if (i + 2 * m >= n) {
          best[i][m] = suffix[i];
          continue;
        }
        int taken = 0;
        for (int x = 1; x <= 2 * m; x++) {
          taken = Math.max(taken, suffix[i] - best[i + x][Math.max(m, x)]);
        }
        best[i][m] = taken;
      }
    }

    return best[0][1];
  }

}
