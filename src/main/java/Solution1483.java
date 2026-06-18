public class Solution1483 {
}

class TreeAncestor {

  private final int[][] up;
  private final int LOG;

  public TreeAncestor(int n, int[] parent) {
    int log = 1;
    while ((1 << log) <= n) {
      log++;
    }
    LOG = log;
    up = new int[LOG][n];
    up[0] = parent.clone();
    for (int j = 1; j < LOG; j++) {
      for (int i = 0; i < n; i++) {
        int prev = up[j - 1][i];
        up[j][i] = prev == -1 ? -1 : up[j - 1][prev];
      }
    }
  }

  public int getKthAncestor(int node, int k) {
    for (int j = 0; j < LOG && node != -1; j++) {
      if ((k & (1 << j)) != 0) {
        node = up[j][node];
      }
    }
    return node;
  }
}
