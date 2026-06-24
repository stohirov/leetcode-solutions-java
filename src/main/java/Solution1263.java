import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Solution1263 {

  private int m, n;
  private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public int minPushBox(char[][] grid) {
    m = grid.length;
    n = grid[0].length;
    int[] box = null, target = null, person = null;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        char c = grid[i][j];
        if (c == 'B') box = new int[]{i, j};
        else if (c == 'T') target = new int[]{i, j};
        else if (c == 'S') person = new int[]{i, j};
      }
    }

    Deque<int[]> queue = new ArrayDeque<>();
    Set<String> visited = new HashSet<>();
    queue.offer(new int[]{box[0], box[1], person[0], person[1], 0});
    visited.add(key(box[0], box[1], person[0], person[1]));
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int br = cur[0], bc = cur[1], pr = cur[2], pc = cur[3], pushes = cur[4];
      if (br == target[0] && bc == target[1]) return pushes;
      for (int[] d : DIRS) {
        int nbr = br + d[0], nbc = bc + d[1];
        int needPr = br - d[0], needPc = bc - d[1];
        if (!valid(grid, nbr, nbc) || !valid(grid, needPr, needPc)) continue;
        if (!personCanReach(grid, pr, pc, needPr, needPc, br, bc)) continue;
        String k = key(nbr, nbc, br, bc);
        if (visited.add(k)) {
          queue.offer(new int[]{nbr, nbc, br, bc, pushes + 1});
        }
      }
    }
    return -1;
  }

  private boolean personCanReach(char[][] grid, int sr, int sc, int tr, int tc,
                                 int boxR, int boxC) {
    if (sr == tr && sc == tc) return true;
    Deque<int[]> q = new ArrayDeque<>();
    boolean[][] seen = new boolean[m][n];
    q.offer(new int[]{sr, sc});
    seen[sr][sc] = true;
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      for (int[] d : DIRS) {
        int nr = cur[0] + d[0], nc = cur[1] + d[1];
        if (!valid(grid, nr, nc) || seen[nr][nc]) continue;
        if (nr == boxR && nc == boxC) continue;
        if (nr == tr && nc == tc) return true;
        seen[nr][nc] = true;
        q.offer(new int[]{nr, nc});
      }
    }
    return false;
  }

  private boolean valid(char[][] grid, int r, int c) {
    return r >= 0 && r < m && c >= 0 && c < n && grid[r][c] != '#';
  }

  private String key(int br, int bc, int pr, int pc) {
    return br + "," + bc + "," + pr + "," + pc;
  }
}
