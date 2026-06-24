import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1368 {

  public int minCost(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][] dist = new int[m][n];
    for (int[] row : dist) java.util.Arrays.fill(row, Integer.MAX_VALUE);
    Deque<int[]> deque = new ArrayDeque<>();
    dist[0][0] = 0;
    deque.offerFirst(new int[]{0, 0});
    while (!deque.isEmpty()) {
      int[] cur = deque.pollFirst();
      int r = cur[0], c = cur[1];
      for (int k = 0; k < 4; k++) {
        int nr = r + dirs[k][0], nc = c + dirs[k][1];
        if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
        int cost = (grid[r][c] == k + 1) ? 0 : 1;
        if (dist[r][c] + cost < dist[nr][nc]) {
          dist[nr][nc] = dist[r][c] + cost;
          if (cost == 0) deque.offerFirst(new int[]{nr, nc});
          else deque.offerLast(new int[]{nr, nc});
        }
      }
    }
    return dist[m - 1][n - 1];
  }
}
