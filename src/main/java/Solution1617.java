public class Solution1617 {
  public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
    int[][] dist = new int[n][n];
    int INF = 1 << 29;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        dist[i][j] = (i == j) ? 0 : INF;
      }
    }
    for (int[] e : edges) {
      int u = e[0] - 1;
      int v = e[1] - 1;
      dist[u][v] = 1;
      dist[v][u] = 1;
    }
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }
    int[] result = new int[n - 1];
    for (int mask = 1; mask < (1 << n); mask++) {
      int count = Integer.bitCount(mask);
      if (count < 2) {
        continue;
      }
      int diameter = 0;
      int edgeCount = 0;
      for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) == 0) {
          continue;
        }
        for (int j = i + 1; j < n; j++) {
          if ((mask & (1 << j)) == 0) {
            continue;
          }
          diameter = Math.max(diameter, dist[i][j]);
          if (dist[i][j] == 1) {
            edgeCount++;
          }
        }
      }
      if (edgeCount == count - 1 && diameter >= 1) {
        result[diameter - 1]++;
      }
    }
    return result;
  }
}
