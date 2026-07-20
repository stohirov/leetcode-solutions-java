import java.util.*;

public class Solution1260 {
  public List<List<Integer>> shiftGrid(int[][] grid, int k) {
    int m = grid.length, n = grid[0].length, total = m * n;
    k %= total;
    List<List<Integer>> res = new ArrayList<>(m);
    for (int i = 0; i < m; i++) {
      List<Integer> row = new ArrayList<>(n);
      for (int j = 0; j < n; j++) {
        int src = ((i * n + j) - k + total) % total;
        row.add(grid[src / n][src % n]);
      }
      res.add(row);
    }
    return res;
  }
}
