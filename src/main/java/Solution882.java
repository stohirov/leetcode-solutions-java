import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution882 {

  public int reachableNodes(int[][] edges, int maxMoves, int n) {
    List<int[]>[] graph = new List[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] e : edges) {
      graph[e[0]].add(new int[] {e[1], e[2]});
      graph[e[1]].add(new int[] {e[0], e[2]});
    }

    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[0] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    pq.offer(new int[] {0, 0});

    boolean[] done = new boolean[n];
    int reachedNodes = 0;
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int u = cur[0], d = cur[1];
      if (done[u]) {
        continue;
      }
      done[u] = true;
      if (d <= maxMoves) {
        reachedNodes++;
      }
      for (int[] nb : graph[u]) {
        int v = nb[0], cnt = nb[1];
        int nd = d + cnt + 1;
        if (nd < dist[v]) {
          dist[v] = nd;
          pq.offer(new int[] {v, nd});
        }
      }
    }

    int reachedSubdivided = 0;
    for (int[] e : edges) {
      int u = e[0], v = e[1], cnt = e[2];
      int fromU = dist[u] == Integer.MAX_VALUE ? 0 : Math.max(0, maxMoves - dist[u]);
      int fromV = dist[v] == Integer.MAX_VALUE ? 0 : Math.max(0, maxMoves - dist[v]);
      reachedSubdivided += Math.min(cnt, fromU + fromV);
    }

    return reachedNodes + reachedSubdivided;
  }
}
