import java.util.PriorityQueue;

public class Solution1102 {

  public int maximumMinimumPath(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited = new boolean[m][n];
    // max-heap by cell value
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
    pq.offer(new int[]{0, 0, grid[0][0]});
    visited[0][0] = true;
    int ans = grid[0][0];
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      ans = Math.min(ans, cur[2]);
      if (cur[0] == m - 1 && cur[1] == n - 1) return ans;
      for (int[] d : dirs) {
        int nr = cur[0] + d[0], nc = cur[1] + d[1];
        if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
          visited[nr][nc] = true;
          pq.offer(new int[]{nr, nc, grid[nr][nc]});
        }
      }
    }
    return ans;
  }
}
