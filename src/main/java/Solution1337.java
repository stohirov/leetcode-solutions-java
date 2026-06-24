import java.util.PriorityQueue;

public class Solution1337 {

  public int[] kWeakestRows(int[][] mat, int k) {
    int m = mat.length;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
        a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
    for (int i = 0; i < m; i++) {
      int strength = countSoldiers(mat[i]);
      pq.offer(new int[]{strength, i});
      if (pq.size() > k) pq.poll();
    }
    int[] result = new int[k];
    for (int i = k - 1; i >= 0; i--) {
      result[i] = pq.poll()[1];
    }
    return result;
  }

  private int countSoldiers(int[] row) {
    int lo = 0, hi = row.length;
    while (lo < hi) {
      int mid = (lo + hi) >>> 1;
      if (row[mid] == 1) lo = mid + 1;
      else hi = mid;
    }
    return lo;
  }
}
