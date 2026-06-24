import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution675 {
  public int cutOffTree(List<List<Integer>> forest) {
    if (forest == null || forest.isEmpty() || forest.getFirst().isEmpty()) return -1;
    int m = forest.size(), n = forest.getFirst().size();
    List<int[]> trees = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int h = forest.get(i).get(j);
        if (h > 1) trees.add(new int[]{h, i, j});
      }
    }
    trees.sort(Comparator.comparingInt(a -> a[0]));
    int total = 0;
    int sr = 0, sc = 0;
    for (int[] tree : trees) {
      int steps = bfs(forest, m, n, sr, sc, tree[1], tree[2]);
      if (steps == -1) return -1;
      total += steps;
      sr = tree[1];
      sc = tree[2];
    }
    return total;
  }

  private int bfs(List<List<Integer>> forest, int m, int n, int sr, int sc, int tr, int tc) {
    if (sr == tr && sc == tc) return 0;
    boolean[][] visited = new boolean[m][n];
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{sr, sc});
    visited[sr][sc] = true;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int steps = 0;
    while (!queue.isEmpty()) {
      steps++;
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] cell = queue.poll();
        for (int[] d : dirs) {
          int nr = cell[0] + d[0], nc = cell[1] + d[1];
          if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) continue;
          if (forest.get(nr).get(nc) == 0) continue;
          if (nr == tr && nc == tc) return steps;
          visited[nr][nc] = true;
          queue.offer(new int[]{nr, nc});
        }
      }
    }
    return -1;
  }
}
