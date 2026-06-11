import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution3558 {
  private static final int MOD = 1_000_000_007;

  public int assignEdgeWeights(int[][] edges) {
    int n = edges.length + 1;
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] e : edges) {
      adj.get(e[0]).add(e[1]);
      adj.get(e[1]).add(e[0]);
    }

    int maxDepth = maxDepth(adj, n);

    return power(2, maxDepth - 1);
  }

  private int maxDepth(List<List<Integer>> adj, int n) {
    boolean[] visited = new boolean[n + 1];
    Deque<int[]> queue = new ArrayDeque<>();
    queue.add(new int[] {1, 0});
    visited[1] = true;
    int maxDepth = 0;
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      maxDepth = Math.max(maxDepth, cur[1]);
      for (int next : adj.get(cur[0])) {
        if (!visited[next]) {
          visited[next] = true;
          queue.add(new int[] {next, cur[1] + 1});
        }
      }
    }
    return maxDepth;
  }

  private int power(long base, int exp) {
    long result = 1;
    base %= MOD;
    while (exp > 0) {
      if ((exp & 1) == 1) {
        result = result * base % MOD;
      }
      base = base * base % MOD;
      exp >>= 1;
    }
    return (int) result;
  }
}
