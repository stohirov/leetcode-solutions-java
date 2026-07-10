import java.util.Arrays;
import java.util.Comparator;

public class Solution3534 {
  public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
    Integer[] order = new Integer[n];
    for (int i = 0; i < n; i++) order[i] = i;
    Arrays.sort(order, Comparator.comparingInt(a -> nums[a]));

    int[] val = new int[n];
    int[] pos = new int[n];
    for (int i = 0; i < n; i++) {
      val[i] = nums[order[i]];
      pos[order[i]] = i;
    }

    int[] comp = new int[n];
    for (int i = 1; i < n; i++) {
      comp[i] = comp[i - 1] + (val[i] - val[i - 1] <= maxDiff ? 0 : 1);
    }

    int[] right = new int[n];
    int j = 0;
    for (int i = 0; i < n; i++) {
      if (j < i) j = i;
      while (j + 1 < n && val[j + 1] - val[i] <= maxDiff) j++;
      right[i] = j;
    }

    int LOG = 1;
    while ((1 << LOG) < n) LOG++;
    LOG++;
    int[][] up = new int[LOG][n];
    up[0] = right;
    for (int k = 1; k < LOG; k++) {
      for (int i = 0; i < n; i++) {
        up[k][i] = up[k - 1][up[k - 1][i]];
      }
    }

    int[] ans = new int[queries.length];
    for (int q = 0; q < queries.length; q++) {
      int u = queries[q][0], v = queries[q][1];
      if (u == v) {
        ans[q] = 0;
        continue;
      }
      int a = pos[u], b = pos[v];
      if (a > b) {
        int t = a;
        a = b;
        b = t;
      }
      if (comp[a] != comp[b]) {
        ans[q] = -1;
        continue;
      }

      int cur = a, steps = 0;
      for (int k = LOG - 1; k >= 0; k--) {
        if (up[k][cur] < b) {
          cur = up[k][cur];
          steps += (1 << k);
        }
      }
      ans[q] = steps + 1;
    }
    return ans;
  }
}
