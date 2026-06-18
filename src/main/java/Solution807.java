public class Solution807 {
  public int maxIncreaseKeepingSkyline(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    int[] rowMax = new int[n];
    int[] colMax = new int[m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        rowMax[i] = Math.max(rowMax[i], grid[i][j]);
        colMax[j] = Math.max(colMax[j], grid[i][j]);
      }
    }
    int total = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        total += Math.min(rowMax[i], colMax[j]) - grid[i][j];
      }
    }
    return total;
  }
}
