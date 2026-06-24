import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution1786 {

  private static final int MOD = 1_000_000_007;

  public int countRestrictedPaths(int n, int[][] edges) {
    List<int[]>[] graph = new List[n + 1];
    for (int i = 1; i <= n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] e : edges) {
      graph[e[0]].add(new int[] {e[1], e[2]});
      graph[e[1]].add(new int[] {e[0], e[2]});
    }

    long[] dist = new long[n + 1];
    Arrays.fill(dist, Long.MAX_VALUE);
    dist[n] = 0;
    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
    pq.offer(new long[] {n, 0});
    while (!pq.isEmpty()) {
      long[] cur = pq.poll();
      int u = (int) cur[0];
      long d = cur[1];
      if (d > dist[u]) {
        continue;
      }
      for (int[] nb : graph[u]) {
        int v = nb[0];
        long nd = d + nb[1];
        if (nd < dist[v]) {
          dist[v] = nd;
          pq.offer(new long[] {v, nd});
        }
      }
    }

    Integer[] order = new Integer[n];
    for (int i = 0; i < n; i++) {
      order[i] = i + 1;
    }
    Arrays.sort(order, Comparator.comparingLong(a -> dist[a]));

    long[] dp = new long[n + 1];
    dp[n] = 1;
    for (int idx = 0; idx < n; idx++) {
      int u = order[idx];
      if (u == n) {
        continue;
      }
      long ways = 0;
      for (int[] nb : graph[u]) {
        int v = nb[0];
        if (dist[v] < dist[u]) {
          ways = (ways + dp[v]) % MOD;
        }
      }
      dp[u] = ways;
    }
    return (int) dp[1];
  }
}
