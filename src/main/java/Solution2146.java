import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution2146 {

  public List<List<Integer>> highestRankedKItems(
      int[][] grid, int[] pricing, int[] start, int k) {
    int m = grid.length, n = grid[0].length;
    int low = pricing[0], high = pricing[1];
    boolean[][] visited = new boolean[m][n];
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    List<int[]> candidates = new ArrayList<>();

    Queue<int[]> bfs = new ArrayDeque<>();
    bfs.offer(new int[] {start[0], start[1], 0});
    visited[start[0]][start[1]] = true;

    while (!bfs.isEmpty()) {
      int[] cur = bfs.poll();
      int r = cur[0], c = cur[1], d = cur[2];
      int price = grid[r][c];
      if (price >= low && price <= high) {
        candidates.add(new int[] {d, price, r, c});
      }
      for (int[] dir : dirs) {
        int nr = r + dir[0], nc = c + dir[1];
        if (nr >= 0 && nr < m && nc >= 0 && nc < n
            && !visited[nr][nc] && grid[nr][nc] != 0) {
          visited[nr][nc] = true;
          bfs.offer(new int[] {nr, nc, d + 1});
        }
      }
    }

    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
      if (a[0] != b[0]) {
        return a[0] - b[0];
      }
      if (a[1] != b[1]) {
        return a[1] - b[1];
      }
      if (a[2] != b[2]) {
        return a[2] - b[2];
      }
      return a[3] - b[3];
    });
    heap.addAll(candidates);

    List<List<Integer>> result = new ArrayList<>();
    while (!heap.isEmpty() && result.size() < k) {
      int[] c = heap.poll();
      result.add(List.of(c[2], c[3]));
    }
    return result;
  }
}
