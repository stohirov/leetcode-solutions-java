import java.util.Arrays;

public class Solution1724 {
  // DSU that records, for each node, the maximum edge weight along the path
  // when it was merged. We build an offline structure: sort edges by weight,
  // union them, and remember a "time" (edge weight) for each union so queries
  // can answer connectivity under a strict weight limit.
  private final int[] parent;
  private final int[] rank_;
  private final int[] maxWeight; // maxWeight[i] = weight that connected i to its parent at union time

  public Solution1724(int n, int[][] edgeList) {
    parent = new int[n];
    rank_ = new int[n];
    maxWeight = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      maxWeight[i] = Integer.MAX_VALUE;
    }
    Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
    for (int[] e : edgeList) {
      union(e[0], e[1], e[2]);
    }
  }

  public boolean query(int p, int q, int limit) {
    // walk up from both, allowing only edges with weight strictly less than limit
    int rp = findUnderLimit(p, limit);
    int rq = findUnderLimit(q, limit);
    return rp == rq;
  }

  private int findUnderLimit(int x, int limit) {
    // climb while the connecting edge weight is < limit
    while (parent[x] != x && maxWeight[x] < limit) {
      x = parent[x];
    }
    return x;
  }

  private void union(int a, int b, int w) {
    int ra = findRoot(a), rb = findRoot(b);
    if (ra == rb) return;
    // union by rank; the merged child's connecting weight is w
    if (rank_[ra] < rank_[rb]) {
      int tmp = ra; ra = rb; rb = tmp;
    }
    parent[rb] = ra;
    maxWeight[rb] = w;
    if (rank_[ra] == rank_[rb]) rank_[ra]++;
  }

  private int findRoot(int x) {
    while (parent[x] != x) {
      x = parent[x];
    }
    return x;
  }
}
