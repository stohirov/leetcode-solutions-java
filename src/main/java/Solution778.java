import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution778 {

  public int swimInWater(int[][] grid) {
    int n = grid.length;
    boolean[][] visited = new boolean[n][n];
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    pq.offer(new int[] {grid[0][0], 0, 0});
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int result = 0;

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int t = cur[0], r = cur[1], c = cur[2];
      if (visited[r][c]) {
        continue;
      }
      visited[r][c] = true;
      result = Math.max(result, t);
      if (r == n - 1 && c == n - 1) {
        return result;
      }
      for (int[] d : dirs) {
        int nr = r + d[0], nc = c + d[1];
        if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
          pq.offer(new int[] {grid[nr][nc], nr, nc});
        }
      }
    }
    return result;
  }
}
