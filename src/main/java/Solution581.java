import java.util.*;

public class Solution581 {
  public int findUnsortedSubarray(int[] nums) {
    int n = nums.length;
    int left = -1, right = -2;
    int max = nums[0], min = nums[n - 1];
    for (int i = 1; i < n; i++) {
      max = Math.max(max, nums[i]);
      if (nums[i] < max) right = i;
    }
    for (int i = n - 2; i >= 0; i--) {
      min = Math.min(min, nums[i]);
      if (nums[i] > min) left = i;
    }
    return right - left + 1;
  }
}
