import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Solution3620 {
  private int n;
  private List<int[]>[] adj;
  private boolean[] online;
  private long k;

  public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
    this.n = online.length;
    this.online = online;
    this.k = k;

    adj = new List[n];
    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<>();
    }

    TreeSet<Integer> costSet = new TreeSet<>();
    for (int[] e : edges) {
      adj[e[0]].add(new int[] {e[1], e[2]});
      costSet.add(e[2]);
    }

    int[] costs = new int[costSet.size()];
    int idx = 0;
    for (int c : costSet) {
      costs[idx++] = c;
    }

    int lo = 0, hi = costs.length - 1, ans = -1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (feasible(costs[mid])) {
        ans = costs[mid];
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return ans;
  }

  private boolean feasible(int threshold) {
    long[] dist = new long[n];
    Arrays.fill(dist, Long.MAX_VALUE);
    dist[0] = 0;

    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
    pq.offer(new long[] {0, 0});

    while (!pq.isEmpty()) {
      long[] top = pq.poll();
      long d = top[0];
      int u = (int) top[1];
      if (d > dist[u]) {
        continue;
      }
      if (u == n - 1) {
        return d <= k;
      }
      for (int[] edge : adj[u]) {
        int v = edge[0];
        int cost = edge[1];
        if (cost < threshold || !online[v]) {
          continue;
        }
        long nd = d + cost;
        if (nd <= k && nd < dist[v]) {
          dist[v] = nd;
          pq.offer(new long[] {nd, v});
        }
      }
    }
    return false;
  }
}
