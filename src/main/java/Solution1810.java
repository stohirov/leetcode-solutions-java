public class Solution1810 {

  // Nested interface so the file compiles standalone. Mirrors the LeetCode
  // GridMaster API for the "Minimum Path Cost in a Hidden Grid" problem.
  interface GridMaster {
    boolean canMove(char direction);

    int move(char direction);

    boolean isTarget();
  }

  private static final char[] DIRS = {'U', 'D', 'L', 'R'};
  private static final char[] OPP = {'D', 'U', 'R', 'L'};
  private static final int[] DX = {-1, 1, 0, 0};
  private static final int[] DY = {0, 0, -1, 1};
  private static final int OFFSET = 500;
  private static final int SIZE = 1001;

  // cost[x][y]: cost to enter cell (x,y); Integer.MIN_VALUE => unknown/unvisited.
  private final int[][] cost = new int[SIZE][SIZE];
  private int targetX = -1, targetY = -1;

  public int findShortestPath(GridMaster master) {
    for (int[] row : cost) {
      java.util.Arrays.fill(row, Integer.MIN_VALUE);
    }
    cost[OFFSET][OFFSET] = 0; // start cell, no entering cost
    explore(master, OFFSET, OFFSET);
    if (targetX == -1) {
      return -1;
    }

    // Dijkstra over discovered cells using known entering costs.
    int[][] dist = new int[SIZE][SIZE];
    for (int[] row : dist) {
      java.util.Arrays.fill(row, Integer.MAX_VALUE);
    }
    java.util.PriorityQueue<int[]> pq =
        new java.util.PriorityQueue<>((a, b) -> a[0] - b[0]);
    dist[OFFSET][OFFSET] = 0;
    pq.offer(new int[] {0, OFFSET, OFFSET});
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int d = cur[0], x = cur[1], y = cur[2];
      if (d > dist[x][y]) {
        continue;
      }
      if (x == targetX && y == targetY) {
        return d;
      }
      for (int i = 0; i < 4; i++) {
        int nx = x + DX[i], ny = y + DY[i];
        if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
          continue;
        }
        if (cost[nx][ny] == Integer.MIN_VALUE) {
          continue; // unreachable / unknown cell
        }
        int nd = d + cost[nx][ny];
        if (nd < dist[nx][ny]) {
          dist[nx][ny] = nd;
          pq.offer(new int[] {nd, nx, ny});
        }
      }
    }
    return dist[targetX][targetY] == Integer.MAX_VALUE ? -1 : dist[targetX][targetY];
  }

  // DFS to discover the whole reachable grid and record entering cost per cell.
  private void explore(GridMaster master, int x, int y) {
    if (master.isTarget()) {
      targetX = x;
      targetY = y;
    }
    for (int i = 0; i < 4; i++) {
      char dir = DIRS[i];
      int nx = x + DX[i], ny = y + DY[i];
      if (!master.canMove(dir) || cost[nx][ny] != Integer.MIN_VALUE) {
        continue;
      }
      int c = master.move(dir);
      cost[nx][ny] = c;
      explore(master, nx, ny);
      master.move(OPP[i]); // backtrack
    }
  }
}
