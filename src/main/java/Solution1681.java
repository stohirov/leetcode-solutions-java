import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution1681 {
  public int minimumIncompatibility(int[] nums, int k) {
    int n = nums.length;
    if (n % k != 0) {
      return -1;
    }
    int groupSize = n / k;
    int[] freq = new int[n + 1];
    for (int num : nums) {
      if (++freq[num] > k) {
        return -1;
      }
    }
    if (groupSize == 1) {
      return 0;
    }
    int full = (1 << n) - 1;
    int[] incomp = new int[1 << n];
    Arrays.fill(incomp, -1);
    for (int mask = 0; mask <= full; mask++) {
      if (Integer.bitCount(mask) != groupSize) {
        continue;
      }
      Set<Integer> seen = new HashSet<>();
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      boolean valid = true;
      for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) != 0) {
          if (!seen.add(nums[i])) {
            valid = false;
            break;
          }
          min = Math.min(min, nums[i]);
          max = Math.max(max, nums[i]);
        }
      }
      if (valid) {
        incomp[mask] = max - min;
      }
    }
    int[] dp = new int[1 << n];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int mask = 0; mask <= full; mask++) {
      if (dp[mask] == Integer.MAX_VALUE) {
        continue;
      }
      int remaining = full ^ mask;
      for (int sub = remaining; sub > 0; sub = (sub - 1) & remaining) {
        if (Integer.bitCount(sub) == groupSize && incomp[sub] >= 0) {
          int next = mask | sub;
          int cost = dp[mask] + incomp[sub];
          if (cost < dp[next]) {
            dp[next] = cost;
          }
        }
      }
    }
    return dp[full] == Integer.MAX_VALUE ? -1 : dp[full];
  }
}
