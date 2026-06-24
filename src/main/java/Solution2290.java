import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution2290 {
  public int minimumObstacles(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dist = new int[m][n];
    for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
    dist[0][0] = grid[0][0];
    Deque<int[]> deque = new ArrayDeque<>();
    deque.offerFirst(new int[] {0, 0});
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    while (!deque.isEmpty()) {
      int[] cur = deque.pollFirst();
      int r = cur[0], c = cur[1];
      for (int[] d : dirs) {
        int nr = r + d[0], nc = c + d[1];
        if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
        int nd = dist[r][c] + grid[nr][nc];
        if (nd < dist[nr][nc]) {
          dist[nr][nc] = nd;
          if (grid[nr][nc] == 0) deque.offerFirst(new int[] {nr, nc});
          else deque.offerLast(new int[] {nr, nc});
        }
      }
    }
    return dist[m - 1][n - 1];
  }
}
