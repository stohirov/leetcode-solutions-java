import java.util.*;

public class Solution1144 {
  public int movesToMakeZigzag(int[] nums) {
    int n = nums.length;
    int[] cost = new int[2];
    for (int parity = 0; parity < 2; parity++) {
      for (int i = parity; i < n; i += 2) {
        int left = i > 0 ? nums[i - 1] : Integer.MAX_VALUE;
        int right = i < n - 1 ? nums[i + 1] : Integer.MAX_VALUE;
        int target = Math.min(left, right) - 1;
        if (nums[i] > target) {
          cost[parity] += nums[i] - target;
        }
      }
    }
    return Math.min(cost[0], cost[1]);
  }
}
