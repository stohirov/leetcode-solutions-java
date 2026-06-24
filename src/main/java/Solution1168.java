import java.util.ArrayList;
import java.util.List;

public class Solution1168 {

  private int[] parent;

  public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    // Add a virtual node 0 connected to each house with cost = well cost.
    List<int[]> edges = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      edges.add(new int[]{0, i + 1, wells[i]});
    }
    for (int[] p : pipes) {
      edges.add(new int[]{p[0], p[1], p[2]});
    }
    edges.sort((a, b) -> a[2] - b[2]);
    parent = new int[n + 1];
    for (int i = 0; i <= n; i++) parent[i] = i;
    int total = 0;
    for (int[] e : edges) {
      if (union(e[0], e[1])) total += e[2];
    }
    return total;
  }

  private int find(int x) {
    while (parent[x] != x) {
      parent[x] = parent[parent[x]];
      x = parent[x];
    }
    return x;
  }

  private boolean union(int a, int b) {
    int ra = find(a), rb = find(b);
    if (ra == rb) return false;
    parent[ra] = rb;
    return true;
  }
}
