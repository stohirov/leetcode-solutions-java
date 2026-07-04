import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution2492 {
  public int minScore(int n, int[][] roads) {
    List<int[]>[] adj = new List[n + 1];
    for (int i = 1; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int[] r : roads) {
      adj[r[0]].add(new int[] {r[1], r[2]});
      adj[r[1]].add(new int[] {r[0], r[2]});
    }

    boolean[] visited = new boolean[n + 1];
    Deque<Integer> queue = new ArrayDeque<>();
    queue.offer(1);
    visited[1] = true;

    int ans = Integer.MAX_VALUE;
    while (!queue.isEmpty()) {
      int u = queue.poll();
      for (int[] edge : adj[u]) {
        int v = edge[0];
        int dist = edge[1];
        ans = Math.min(ans, dist);
        if (!visited[v]) {
          visited[v] = true;
          queue.offer(v);
        }
      }
    }
    return ans;
  }
}
