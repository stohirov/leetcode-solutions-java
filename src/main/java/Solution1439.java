import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1439 {
  public int kthSmallest(int[][] mat, int k) {
    int[] row = mat[0].clone();
    int[] cur = kSmallestSums(row, k);
    for (int i = 1; i < mat.length; i++) {
      cur = combine(cur, mat[i], k);
    }
    return cur[k - 1];
  }

  private int[] kSmallestSums(int[] a, int k) {
    int len = Math.min(k, a.length);
    int[] res = new int[len];
    System.arraycopy(a, 0, res, 0, len);
    return res;
  }

  private int[] combine(int[] acc, int[] row, int k) {
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
    boolean[][] seen = new boolean[acc.length][row.length];
    pq.offer(new int[] {acc[0] + row[0], 0, 0});
    seen[0][0] = true;

    int[] result = new int[Math.min(k, acc.length * row.length)];
    int count = 0;
    while (count < result.length && !pq.isEmpty()) {
      int[] top = pq.poll();
      result[count++] = top[0];
      int i = top[1], j = top[2];
      if (i + 1 < acc.length && !seen[i + 1][j]) {
        seen[i + 1][j] = true;
        pq.offer(new int[] {acc[i + 1] + row[j], i + 1, j});
      }
      if (j + 1 < row.length && !seen[i][j + 1]) {
        seen[i][j + 1] = true;
        pq.offer(new int[] {acc[i] + row[j + 1], i, j + 1});
      }
    }
    return result;
  }
}
