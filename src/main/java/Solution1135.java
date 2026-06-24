import java.util.Arrays;

public class Solution1135 {

  private int[] parent;

  public int minimumCost(int n, int[][] connections) {
    parent = new int[n + 1];
    for (int i = 0; i <= n; i++) parent[i] = i;
    Arrays.sort(connections, (a, b) -> a[2] - b[2]);
    int total = 0, used = 0;
    for (int[] c : connections) {
      if (union(c[0], c[1])) {
        total += c[2];
        used++;
      }
    }
    return used == n - 1 ? total : -1;
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
