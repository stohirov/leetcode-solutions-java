import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution1878 {

  public int[] getBiggestThree(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    Set<Integer> seen = new HashSet<>();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        for (int d = 0; ; d++) {
          int top = i - d, bottom = i + d;
          int left = j - d, right = j + d;
          if (top < 0 || bottom >= m || left < 0 || right >= n) {
            break;
          }
          int sum = rhombusSum(grid, i, j, d);
          if (seen.add(sum)) {
            pq.offer(sum);
            if (pq.size() > 3) {
              int removed = pq.poll();
              seen.remove(removed);
            }
          }
        }
      }
    }

    int[] ans = new int[pq.size()];
    for (int idx = ans.length - 1; idx >= 0; idx--) {
      ans[idx] = pq.poll();
    }
    return ans;
  }

  private int rhombusSum(int[][] grid, int ci, int cj, int d) {
    if (d == 0) {
      return grid[ci][cj];
    }
    int sum = 0;
    int x = ci - d, y = cj;
    for (int s = 0; s < d; s++) {
      sum += grid[x][y];
      x++;
      y++;
    }
    for (int s = 0; s < d; s++) {
      sum += grid[x][y];
      x++;
      y--;
    }
    for (int s = 0; s < d; s++) {
      sum += grid[x][y];
      x--;
      y--;
    }
    for (int s = 0; s < d; s++) {
      sum += grid[x][y];
      x--;
      y++;
    }
    return sum;
  }
}
