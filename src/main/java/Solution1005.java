import java.util.*;

public class Solution1005 {
  public int largestSumAfterKNegations(int[] nums, int k) {
    Arrays.sort(nums);
    int i = 0;
    while (k > 0 && i < nums.length && nums[i] < 0) {
      nums[i] = -nums[i];
      i++;
      k--;
    }
    int sum = 0, min = Integer.MAX_VALUE;
    for (int x : nums) {
      sum += x;
      min = Math.min(min, x);
    }
    if (k % 2 == 1) sum -= 2 * min;
    return sum;
  }
}
