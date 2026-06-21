import java.util.*;

public class Solution1840 {
  public int maxBuilding(int n, int[][] restrictions) {
    List<int[]> r = new ArrayList<>();
    r.add(new int[]{1, 0});
    Collections.addAll(r, restrictions);
    r.sort(Comparator.comparingInt(a -> a[0]));

    int m = r.size();

    for (int i = 1; i < m; i++) {
      int dist = r.get(i)[0] - r.get(i - 1)[0];
      r.get(i)[1] = Math.min(r.get(i)[1], r.get(i - 1)[1] + dist);
    }

    for (int i = m - 2; i >= 0; i--) {
      int dist = r.get(i + 1)[0] - r.get(i)[0];
      r.get(i)[1] = Math.min(r.get(i)[1], r.get(i + 1)[1] + dist);
    }

    int ans = 0;

    for (int i = 1; i < m; i++) {
      int id1 = r.get(i - 1)[0], h1 = r.get(i - 1)[1];
      int id2 = r.get(i)[0], h2 = r.get(i)[1];
      int dist = id2 - id1;
      int peak = (h1 + h2 + dist) / 2;
      ans = Math.max(ans, peak);
    }

    int[] last = r.get(m - 1);
    ans = Math.max(ans, last[1] + (n - last[0]));
    return ans;
  }
}
