import java.util.Arrays;

public class Solution698 {

  public boolean canPartitionKSubsets(int[] nums, int k) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    if (sum % k != 0) {
      return false;
    }
    int target = sum / k;
    Arrays.sort(nums);
    if (nums[nums.length - 1] > target) {
      return false;
    }
    boolean[] used = new boolean[nums.length];
    return backtrack(nums, used, nums.length - 1, 0, target, k);
  }

  private boolean backtrack(int[] nums, boolean[] used, int start, int currentSum,
      int target, int remainingBuckets) {
    if (remainingBuckets == 0) {
      return true;
    }
    if (currentSum == target) {
      return backtrack(nums, used, nums.length - 1, 0, target, remainingBuckets - 1);
    }
    for (int i = start; i >= 0; i--) {
      if (used[i] || currentSum + nums[i] > target) {
        continue;
      }
      used[i] = true;
      if (backtrack(nums, used, i - 1, currentSum + nums[i], target, remainingBuckets)) {
        return true;
      }
      used[i] = false;
      if (currentSum == 0) {
        break;
      }
      while (i > 0 && nums[i - 1] == nums[i]) {
        i--;
      }
    }
    return false;
  }
}
