import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution407 {
  public int trapRainWater(int[][] heightMap) {
    if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) return 0;
    int m = heightMap.length, n = heightMap[0].length;
    if (m < 3 || n < 3) return 0;
    boolean[][] visited = new boolean[m][n];
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
          pq.offer(new int[]{heightMap[i][j], i, j});
          visited[i][j] = true;
        }
      }
    }
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int water = 0;
    while (!pq.isEmpty()) {
      int[] cell = pq.poll();
      int h = cell[0], r = cell[1], c = cell[2];
      for (int[] d : dirs) {
        int nr = r + d[0], nc = c + d[1];
        if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) continue;
        visited[nr][nc] = true;
        if (heightMap[nr][nc] < h) water += h - heightMap[nr][nc];
        pq.offer(new int[]{Math.max(h, heightMap[nr][nc]), nr, nc});
      }
    }
    return water;
  }
}
