import java.util.*;

public class Solution3286 {
  public boolean findSafeWalk(List<List<Integer>> grid, int health) {
    int m = grid.size(), n = grid.getFirst().size();
    int[][] cost = new int[m][n];
    for (int[] row : cost) Arrays.fill(row, Integer.MAX_VALUE);

    int start = grid.getFirst().getFirst();
    cost[0][0] = start;
    Deque<int[]> dq = new ArrayDeque<>();
    dq.offerFirst(new int[] {0, 0});

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    while (!dq.isEmpty()) {
      int[] cur = dq.pollFirst();
      int r = cur[0], c = cur[1];
      for (int[] d : dirs) {
        int nr = r + d[0], nc = c + d[1];
        if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
        int nCost = cost[r][c] + grid.get(nr).get(nc);
        if (nCost < cost[nr][nc]) {
          cost[nr][nc] = nCost;
          if (grid.get(nr).get(nc) == 1) dq.offerLast(new int[] {nr, nc});
          else dq.offerFirst(new int[] {nr, nc});
        }
      }
    }
    return cost[m - 1][n - 1] < health;
  }
}
