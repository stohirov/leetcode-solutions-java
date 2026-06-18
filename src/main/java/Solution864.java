import java.util.ArrayDeque;
import java.util.Queue;

public class Solution864 {

  public int shortestPathAllKeys(String[] grid) {
    int m = grid.length;
    int n = grid[0].length();
    int startR = 0, startC = 0, allKeys = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        char c = grid[i].charAt(j);
        if (c == '@') {
          startR = i;
          startC = j;
        } else if (c >= 'a' && c <= 'f') {
          allKeys |= (1 << (c - 'a'));
        }
      }
    }
    
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    boolean[][][] visited = new boolean[m][n][allKeys + 1];
    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[] {startR, startC, 0});
    visited[startR][startC][0] = true;
    int steps = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int s = 0; s < size; s++) {
        int[] cur = queue.poll();
        int r = cur[0], c = cur[1], keys = cur[2];
        if (keys == allKeys) {
          return steps;
        }
        for (int[] d : dirs) {
          int nr = r + d[0];
          int nc = c + d[1];
          if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
            continue;
          }
          char ch = grid[nr].charAt(nc);
          if (ch == '#') {
            continue;
          }
          int nKeys = keys;
          if (ch >= 'A' && ch <= 'F') {
            if ((keys & (1 << (ch - 'A'))) == 0) {
              continue;
            }
          } else if (ch >= 'a' && ch <= 'f') {
            nKeys = keys | (1 << (ch - 'a'));
          }
          if (!visited[nr][nc][nKeys]) {
            visited[nr][nc][nKeys] = true;
            queue.offer(new int[] {nr, nc, nKeys});
          }
        }
      }
      steps++;
    }
    return -1;
  }
}
