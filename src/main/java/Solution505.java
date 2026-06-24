import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution505 {
  public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    int m = maze.length, n = maze[0].length;
    int[][] dist = new int[m][n];
    for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    dist[start[0]][start[1]] = 0;
    pq.offer(new int[]{0, start[0], start[1]});
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int d = cur[0], r = cur[1], c = cur[2];
      if (d > dist[r][c]) continue;
      if (r == destination[0] && c == destination[1]) return d;
      for (int[] dir : dirs) {
        int nr = r, nc = c, steps = 0;
        while (true) {
          int tr = nr + dir[0], tc = nc + dir[1];
          if (tr < 0 || tr >= m || tc < 0 || tc >= n || maze[tr][tc] == 1) break;
          nr = tr;
          nc = tc;
          steps++;
        }
        int nd = d + steps;
        if (nd < dist[nr][nc]) {
          dist[nr][nc] = nd;
          pq.offer(new int[]{nd, nr, nc});
        }
      }
    }
    return -1;
  }
}
