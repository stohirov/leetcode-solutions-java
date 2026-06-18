import java.util.HashMap;
import java.util.Map;

public class Solution1655 {
  private int[] counts;

  public boolean canDistribute(int[] nums, int[] quantity) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int num : nums) {
      freq.merge(num, 1, Integer::sum);
    }
    counts = new int[freq.size()];
    int idx = 0;
    for (int c : freq.values()) {
      counts[idx++] = c;
    }
    int m = quantity.length;
    int[] subSum = new int[1 << m];
    for (int mask = 1; mask < (1 << m); mask++) {
      int low = Integer.numberOfTrailingZeros(mask);
      subSum[mask] = subSum[mask ^ (1 << low)] + quantity[low];
    }
    int full = (1 << m) - 1;
    Boolean[][] memo = new Boolean[counts.length][1 << m];
    return dfs(0, full, subSum, memo);
  }

  private boolean dfs(int i, int remaining, int[] subSum, Boolean[][] memo) {
    if (remaining == 0) {
      return true;
    }
    if (i == counts.length) {
      return false;
    }
    if (memo[i][remaining] != null) {
      return memo[i][remaining];
    }
    boolean result = false;
    for (int sub = remaining; sub > 0; sub = (sub - 1) & remaining) {
      if (subSum[sub] <= counts[i] && dfs(i + 1, remaining ^ sub, subSum, memo)) {
        result = true;
        break;
      }
    }
    if (!result) {
      result = dfs(i + 1, remaining, subSum, memo);
    }
    memo[i][remaining] = result;
    return result;
  }
}
