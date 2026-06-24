import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2386 {
  public long kSum(int[] nums, int k) {
    int n = nums.length;
    long maxSum = 0;
    for (int i = 0; i < n; i++) {
      if (nums[i] > 0) maxSum += nums[i];
      else nums[i] = -nums[i];
    }
    Arrays.sort(nums);
    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
    if (n > 0) pq.offer(new long[] {nums[0], 0});
    long loss = 0;
    for (int t = 2; t <= k; t++) {
      long[] cur = pq.poll();
      loss = cur[0];
      int idx = (int) cur[1];
      if (idx + 1 < n) {
        pq.offer(new long[] {loss + nums[idx + 1], idx + 1});
        pq.offer(new long[] {loss - nums[idx] + nums[idx + 1], idx + 1});
      }
    }
    return maxSum - loss;
  }
}
