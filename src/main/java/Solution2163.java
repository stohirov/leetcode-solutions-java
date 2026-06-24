import java.util.PriorityQueue;

public class Solution2163 {

  public long minimumDifference(int[] nums) {
    int total = nums.length;
    int n = total / 3;

    long[] prefixMin = new long[total + 1];
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    long sum = 0;
    for (int i = 0; i < total; i++) {
      maxHeap.offer(nums[i]);
      sum += nums[i];
      if (maxHeap.size() > n) {
        sum -= maxHeap.poll();
      }
      if (maxHeap.size() == n) {
        prefixMin[i + 1] = sum;
      } else {
        prefixMin[i + 1] = Long.MAX_VALUE;
      }
    }

    long[] suffixMax = new long[total + 1];
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    sum = 0;
    for (int i = total - 1; i >= 0; i--) {
      minHeap.offer(nums[i]);
      sum += nums[i];
      if (minHeap.size() > n) {
        sum -= minHeap.poll();
      }
      if (minHeap.size() == n) {
        suffixMax[i] = sum;
      } else {
        suffixMax[i] = Long.MIN_VALUE;
      }
    }

    long ans = Long.MAX_VALUE;
    for (int i = n; i <= 2 * n; i++) {
      if (prefixMin[i] != Long.MAX_VALUE && suffixMax[i] != Long.MIN_VALUE) {
        ans = Math.min(ans, prefixMin[i] - suffixMax[i]);
      }
    }
    return ans;
  }
}
