import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1834 {

  public int[] getOrder(int[][] tasks) {
    int n = tasks.length;
    Integer[] idx = new Integer[n];
    for (int i = 0; i < n; i++) {
      idx[i] = i;
    }
    Arrays.sort(idx, Comparator.comparingLong(a -> tasks[a][0]));

    PriorityQueue<int[]> pq =
        new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

    int[] ans = new int[n];
    long time = 0;
    int i = 0, out = 0;
    while (out < n) {
      while (i < n && tasks[idx[i]][0] <= time) {
        pq.offer(new int[] {tasks[idx[i]][1], idx[i]});
        i++;
      }
      if (pq.isEmpty()) {
        time = tasks[idx[i]][0];
        continue;
      }
      int[] task = pq.poll();
      time += task[0];
      ans[out++] = task[1];
    }
    return ans;
  }
}
