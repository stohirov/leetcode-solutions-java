import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Solution3559 {
  static final int MOD = 1_000_000_007;

  public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
    int n = edges.length + 1;
    List<Integer>[] adj = new List[n + 1];
    for (int i = 1; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int[] e : edges) {
      adj[e[0]].add(e[1]);
      adj[e[1]].add(e[0]);
    }

    int log = 1;
    while ((1 << log) < n) {
      log++;
    }
    log++;

    int[] depth = new int[n + 1];
    int[][] up = new int[log][n + 1];
    int[] parent = new int[n + 1];
    boolean[] visited = new boolean[n + 1];

    ArrayDeque<Integer> queue = new ArrayDeque<>();
    queue.add(1);
    visited[1] = true;
    while (!queue.isEmpty()) {
      int u = queue.poll();
      up[0][u] = parent[u];
      for (int v : adj[u]) {
        if (!visited[v]) {
          visited[v] = true;
          parent[v] = u;
          depth[v] = depth[u] + 1;
          queue.add(v);
        }
      }
    }

    for (int j = 1; j < log; j++) {
      for (int v = 1; v <= n; v++) {
        up[j][v] = up[j - 1][up[j - 1][v]];
      }
    }

    int[] pow2 = new int[n + 1];
    pow2[0] = 1;
    for (int i = 1; i <= n; i++) {
      pow2[i] = (int) ((pow2[i - 1] * 2L) % MOD);
    }

    int[] ans = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int u = queries[i][0];
      int v = queries[i][1];
      int l = lca(u, v, depth, up, log);
      int dist = depth[u] + depth[v] - 2 * depth[l];
      ans[i] = dist == 0 ? 0 : pow2[dist - 1];
    }
    return ans;
  }

  private int lca(int u, int v, int[] depth, int[][] up, int log) {
    if (depth[u] < depth[v]) {
      int t = u;
      u = v;
      v = t;
    }
    int diff = depth[u] - depth[v];
    for (int j = 0; j < log; j++) {
      if (((diff >> j) & 1) == 1) {
        u = up[j][u];
      }
    }
    if (u == v) {
      return u;
    }
    for (int j = log - 1; j >= 0; j--) {
      if (up[j][u] != up[j][v]) {
        u = up[j][u];
        v = up[j][v];
      }
    }
    return up[0][u];
  }
}