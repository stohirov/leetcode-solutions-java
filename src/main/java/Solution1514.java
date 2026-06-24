import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution1514 {
  public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
    List<double[]>[] graph = new List[n];
    for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0], v = edges[i][1];
      double p = succProb[i];
      graph[u].add(new double[] {v, p});
      graph[v].add(new double[] {u, p});
    }

    double[] best = new double[n];
    best[start] = 1.0;
    PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
    pq.offer(new double[] {start, 1.0});

    while (!pq.isEmpty()) {
      double[] cur = pq.poll();
      int node = (int) cur[0];
      double prob = cur[1];
      if (node == end) return prob;
      if (prob < best[node]) continue;
      for (double[] nb : graph[node]) {
        int next = (int) nb[0];
        double np = prob * nb[1];
        if (np > best[next]) {
          best[next] = np;
          pq.offer(new double[] {next, np});
        }
      }
    }
    return 0.0;
  }
}
