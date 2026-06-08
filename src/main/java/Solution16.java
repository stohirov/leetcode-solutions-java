import java.util.Arrays;

public class Solution16 {
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int n = nums.length;
    int result = nums[0] + nums[1] + nums[2];

    for (int i = 0; i < n - 2; i++) {
      int left = i + 1;
      int right = n - 1;

      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (Math.abs(sum - target) < Math.abs(result - target)) {
          result = sum;
        }

        if (sum == target) {
          return sum;
        } else if (sum < target) {
          left++;
        } else {
          right--;
        }
      }

    }

    return result;
  }
}
