import java.util.PriorityQueue;

public class Solution499 {
  public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
    int m = maze.length, n = maze[0].length;
    // dirs ordered to keep lexicographic path: d=down, l=left, r=right, u=up
    int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    char[] names = {'d', 'l', 'r', 'u'};
    int[][] dist = new int[m][n];
    String[][] path = new String[m][n];
    for (int[] row : dist) java.util.Arrays.fill(row, Integer.MAX_VALUE);
    // state: {dist, row, col, pathString} — order by dist then path
    PriorityQueue<Object[]> pq = new PriorityQueue<>((a, b) -> {
      int da = (int) a[0], db = (int) b[0];
      if (da != db) return da - db;
      return ((String) a[3]).compareTo((String) b[3]);
    });
    dist[ball[0]][ball[1]] = 0;
    path[ball[0]][ball[1]] = "";
    pq.offer(new Object[]{0, ball[0], ball[1], ""});
    while (!pq.isEmpty()) {
      Object[] cur = pq.poll();
      int d = (int) cur[0], r = (int) cur[1], c = (int) cur[2];
      String p = (String) cur[3];
      if (d > dist[r][c] || (d == dist[r][c] && p.compareTo(path[r][c]) > 0)) continue;
      if (r == hole[0] && c == hole[1]) return p;
      for (int k = 0; k < 4; k++) {
        int nr = r, nc = c, steps = 0;
        while (true) {
          int tr = nr + dirs[k][0], tc = nc + dirs[k][1];
          if (tr < 0 || tr >= m || tc < 0 || tc >= n || maze[tr][tc] == 1) break;
          nr = tr;
          nc = tc;
          steps++;
          if (nr == hole[0] && nc == hole[1]) break;
        }
        int nd = d + steps;
        String np = p + names[k];
        if (nd < dist[nr][nc] || (nd == dist[nr][nc] && np.compareTo(path[nr][nc]) < 0)) {
          dist[nr][nc] = nd;
          path[nr][nc] = np;
          pq.offer(new Object[]{nd, nr, nc, np});
        }
      }
    }
    return "impossible";
  }
}
