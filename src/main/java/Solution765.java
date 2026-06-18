public class Solution765 {

  public int minSwapsCouples(int[] row) {
    int n = row.length;
    int[] parent = new int[n / 2];
    for (int i = 0; i < parent.length; i++) {
      parent[i] = i;
    }
    int groups = parent.length;
    for (int i = 0; i < n; i += 2) {
      int a = row[i] / 2;
      int b = row[i + 1] / 2;
      if (union(parent, a, b)) {
        groups--;
      }
    }
    return parent.length - groups;
  }

  private int find(int[] parent, int x) {
    while (parent[x] != x) {
      parent[x] = parent[parent[x]];
      x = parent[x];
    }
    return x;
  }

  private boolean union(int[] parent, int a, int b) {
    int ra = find(parent, a);
    int rb = find(parent, b);
    if (ra == rb) {
      return false;
    }
    parent[ra] = rb;
    return true;
  }
}
