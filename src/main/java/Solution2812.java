import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution2812 {

  private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  public int maximumSafenessFactor(List<List<Integer>> grid) {
    int n = grid.size();

    int[][] dist = new int[n][n];
    Queue<int[]> queue = new ArrayDeque<>();
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        if (grid.get(r).get(c) == 1) {
          queue.offer(new int[] {r, c});
          dist[r][c] = 0;
        } else {
          dist[r][c] = -1;
        }
      }
    }
    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      for (int[] d : DIRECTIONS) {
        int nr = cell[0] + d[0];
        int nc = cell[1] + d[1];
        if (nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] == -1) {
          dist[nr][nc] = dist[cell[0]][cell[1]] + 1;
          queue.offer(new int[] {nr, nc});
        }
      }
    }

    int[][] safeness = new int[n][n];
    for (int[] row : safeness) {
      Arrays.fill(row, -1);
    }

    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[2] - a[2]);
    safeness[0][0] = dist[0][0];
    heap.offer(new int[] {0, 0, dist[0][0]});
    while (!heap.isEmpty()) {
      int[] cur = heap.poll();
      int r = cur[0];
      int c = cur[1];
      int s = cur[2];
      if (s < safeness[r][c]) {
        continue;
      }
      if (r == n - 1 && c == n - 1) {
        return s;
      }
      for (int[] d : DIRECTIONS) {
        int nr = r + d[0];
        int nc = c + d[1];
        if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
          int ns = Math.min(s, dist[nr][nc]);
          if (ns > safeness[nr][nc]) {
            safeness[nr][nc] = ns;
            heap.offer(new int[] {nr, nc, ns});
          }
        }
      }
    }
    return safeness[n - 1][n - 1];
  }
}
