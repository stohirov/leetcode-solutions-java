import java.util.ArrayDeque;
import java.util.Queue;

public class Solution847 {

  static class State {
    int node;
    int mask;

    State(int node, int mask) {
      this.node = node;
      this.mask = mask;
    }
  }

  public int shortestPathLength(int[][] graph) {
    int n = graph.length;
    int full = (1 << n) - 1;
    boolean[][] visited = new boolean[n][1 << n];
    Queue<State> queue = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      queue.offer(new State(i, 1 << i));
      visited[i][1 << i] = true;
    }
    int steps = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int s = 0; s < size; s++) {
        State cur = queue.poll();
        if (cur.mask == full) {
          return steps;
        }
        for (int next : graph[cur.node]) {
          int nextMask = cur.mask | (1 << next);
          if (!visited[next][nextMask]) {
            visited[next][nextMask] = true;
            queue.offer(new State(next, nextMask));
          }
        }
      }
      steps++;
    }
    return 0;
  }
}
