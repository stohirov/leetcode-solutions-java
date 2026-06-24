import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution743 {
  public int networkDelayTime(int[][] times, int n, int k) {
    List<int[]>[] graph = new List[n + 1];
    for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
    for (int[] t : times) graph[t[0]].add(new int[]{t[1], t[2]});
    int[] dist = new int[n + 1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[k] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    pq.offer(new int[]{0, k});
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int d = cur[0], u = cur[1];
      if (d > dist[u]) continue;
      for (int[] edge : graph[u]) {
        int v = edge[0], w = edge[1];
        if (d + w < dist[v]) {
          dist[v] = d + w;
          pq.offer(new int[]{d + w, v});
        }
      }
    }
    int max = 0;
    for (int i = 1; i <= n; i++) {
      if (dist[i] == Integer.MAX_VALUE) return -1;
      max = Math.max(max, dist[i]);
    }
    return max;
  }
}
