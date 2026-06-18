public class Solution1283 {

  public int smallestDivisor(int[] nums, int threshold) {
    int left = 1;
    int right = 0;
    for (int num : nums) {
      right = Math.max(right, num);
    }
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (sumWithDivisor(nums, mid) <= threshold) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  private int sumWithDivisor(int[] nums, int divisor) {
    int sum = 0;
    for (int num : nums) {
      sum += (num + divisor - 1) / divisor;
    }
    return sum;
  }
}
