public class Solution209 {

  // Minimum Size Subarray Sum. Binary search variant: build prefix sums (sorted
  // since values are positive), then for each start binary search the shortest
  // end whose window sum reaches the target. O(n log n).
  public int minSubArrayLen(int target, int[] nums) {
    int n = nums.length;
    int[] prefix = new int[n + 1];
    for (int i = 0; i < n; i++) {
      prefix[i + 1] = prefix[i] + nums[i];
    }
    int best = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      int need = prefix[i] + target;
      int j = lowerBound(prefix, need);
      if (j <= n) {
        best = Math.min(best, j - i);
      }
    }
    return best == Integer.MAX_VALUE ? 0 : best;
  }

  private int lowerBound(int[] prefix, int target) {
    int left = 0;
    int right = prefix.length;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (prefix[mid] < target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }
}
