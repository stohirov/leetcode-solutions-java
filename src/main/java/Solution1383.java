import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution1383 {
  public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    Integer[] idx = new Integer[n];
    for (int i = 0; i < n; i++) idx[i] = i;
    Arrays.sort(idx, (a, b) -> efficiency[b] - efficiency[a]);

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    long speedSum = 0;
    long best = 0;
    long MOD = 1_000_000_007L;

    for (int i = 0; i < n; i++) {
      int e = efficiency[idx[i]];
      int s = speed[idx[i]];
      minHeap.offer(s);
      speedSum += s;
      if (minHeap.size() > k) {
        speedSum -= minHeap.poll();
      }
      best = Math.max(best, speedSum * (long) e);
    }
    return (int) (best % MOD);
  }
}
