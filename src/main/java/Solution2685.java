public class Solution2685 {
  private int[] parent;
  private int[] size;

  public int countCompleteComponents(int n, int[][] edges) {
    parent = new int[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }

    int[] edgeCount = new int[n];
    for (int[] e : edges) {
      union(e[0], e[1]);
    }
    for (int[] e : edges) {
      edgeCount[find(e[0])]++;
    }

    int complete = 0;
    for (int i = 0; i < n; i++) {
      if (find(i) == i) {
        int v = size[i];
        if (edgeCount[i] == v * (v - 1) / 2) complete++;
      }
    }
    return complete;
  }

  private int find(int x) {
    while (parent[x] != x) {
      parent[x] = parent[parent[x]];
      x = parent[x];
    }
    return x;
  }

  private void union(int a, int b) {
    int ra = find(a), rb = find(b);
    if (ra == rb) return;
    if (size[ra] < size[rb]) {
      int t = ra;
      ra = rb;
      rb = t;
    }
    parent[rb] = ra;
    size[ra] += size[rb];
  }
}
