import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution2093 {

  public int minimumCost(int n, int[][] highways, int discounts) {
    List<int[]>[] graph = new List[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] h : highways) {
      graph[h[0]].add(new int[] {h[1], h[2]});
      graph[h[1]].add(new int[] {h[0], h[2]});
    }

    // best[city][usedDiscounts] = min cost
    int[][] dist = new int[n][discounts + 1];
    for (int[] row : dist) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }
    dist[0][0] = 0;

    // (cost, city, usedDiscounts)
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    pq.offer(new int[] {0, 0, 0});

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int cost = cur[0], city = cur[1], used = cur[2];
      if (city == n - 1) {
        return cost;
      }
      if (cost > dist[city][used]) {
        continue;
      }
      for (int[] next : graph[city]) {
        int nb = next[0], toll = next[1];
        // without discount
        if (cost + toll < dist[nb][used]) {
          dist[nb][used] = cost + toll;
          pq.offer(new int[] {dist[nb][used], nb, used});
        }
        // with discount
        if (used < discounts && cost + toll / 2 < dist[nb][used + 1]) {
          dist[nb][used + 1] = cost + toll / 2;
          pq.offer(new int[] {dist[nb][used + 1], nb, used + 1});
        }
      }
    }
    return -1;
  }
}
