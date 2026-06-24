import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution2203 {
  public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
    List<long[]>[] graph = buildGraph(n, edges, false);
    List<long[]>[] rev = buildGraph(n, edges, true);

    long[] d1 = dijkstra(n, graph, src1);
    long[] d2 = dijkstra(n, graph, src2);
    long[] dd = dijkstra(n, rev, dest);

    long ans = Long.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      if (d1[i] == Long.MAX_VALUE || d2[i] == Long.MAX_VALUE || dd[i] == Long.MAX_VALUE) continue;
      ans = Math.min(ans, d1[i] + d2[i] + dd[i]);
    }
    return ans == Long.MAX_VALUE ? -1 : ans;
  }

  private List<long[]>[] buildGraph(int n, int[][] edges, boolean reverse) {
    List<long[]>[] graph = new List[n];
    for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
    for (int[] e : edges) {
      int from = reverse ? e[1] : e[0];
      int to = reverse ? e[0] : e[1];
      graph[from].add(new long[] {to, e[2]});
    }
    return graph;
  }

  private long[] dijkstra(int n, List<long[]>[] graph, int start) {
    long[] dist = new long[n];
    Arrays.fill(dist, Long.MAX_VALUE);
    dist[start] = 0;
    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
    pq.offer(new long[] {start, 0});
    while (!pq.isEmpty()) {
      long[] cur = pq.poll();
      int u = (int) cur[0];
      long du = cur[1];
      if (du > dist[u]) continue;
      for (long[] edge : graph[u]) {
        int v = (int) edge[0];
        long nd = du + edge[1];
        if (nd < dist[v]) {
          dist[v] = nd;
          pq.offer(new long[] {v, nd});
        }
      }
    }
    return dist;
  }
}
