public class Solution861 {

  public int matrixScore(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      if (grid[i][0] == 0) {
        for (int j = 0; j < n; j++) {
          grid[i][j] ^= 1;
        }
      }
    }
    int score = 0;
    for (int j = 0; j < n; j++) {
      int ones = 0;
      for (int[] ints : grid) {
        ones += ints[j];
      }
      int best = Math.max(ones, m - ones);
      score += best * (1 << (n - 1 - j));
    }
    return score;
  }
}
