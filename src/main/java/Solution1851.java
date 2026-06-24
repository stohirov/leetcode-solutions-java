import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1851 {

  public int[] minInterval(int[][] intervals, int[] queries) {
    int n = queries.length;
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    Integer[] qIdx = new Integer[n];
    for (int i = 0; i < n; i++) {
      qIdx[i] = i;
    }
    Arrays.sort(qIdx, Comparator.comparingInt(a -> queries[a]));

    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    int[] ans = new int[n];
    int i = 0;
    for (int qi : qIdx) {
      int q = queries[qi];
      while (i < intervals.length && intervals[i][0] <= q) {
        int size = intervals[i][1] - intervals[i][0] + 1;
        pq.offer(new int[] {size, intervals[i][1]});
        i++;
      }
      while (!pq.isEmpty() && pq.peek()[1] < q) {
        pq.poll();
      }
      ans[qi] = pq.isEmpty() ? -1 : pq.peek()[0];
    }
    return ans;
  }
}
