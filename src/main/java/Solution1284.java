import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution1284 {
  public int minFlips(int[][] mat) {
    int m = mat.length, n = mat[0].length;
    int start = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (mat[i][j] == 1) {
          start |= (1 << (i * n + j));
        }
      }
    }

    if (start == 0) return 0;
    int[] flip = new int[m * n];
    int[][] dirs = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int mask = 0;
        for (int[] d : dirs) {
          int ni = i + d[0], nj = j + d[1];
          if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
            mask |= (1 << (ni * n + nj));
          }
        }
        flip[i * n + j] = mask;
      }
    }

    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(start);
    visited.add(start);
    int steps = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int k = 0; k < size; k++) {
        int cur = queue.poll();
        if (cur == 0) return steps;
        for (int f : flip) {
          int next = cur ^ f;
          if (visited.add(next)) {
            queue.offer(next);
          }
        }
      }
      steps++;
    }

    return -1;
  }
}
