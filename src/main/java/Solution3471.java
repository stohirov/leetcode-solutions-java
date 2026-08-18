import java.util.HashMap;
import java.util.Map;

public class Solution3471 {
  public int largestInteger(int[] nums, int k) {
    int n = nums.length;
    if (k == n) {
      int best = -1;
      for (int x : nums) best = Math.max(best, x);
      return best;
    }

    Map<Integer, Integer> count = new HashMap<>();
    for (int x : nums) count.merge(x, 1, Integer::sum);

    int best = -1;
    if (k == 1) {
      for (Map.Entry<Integer, Integer> e : count.entrySet()) {
        if (e.getValue() == 1) best = Math.max(best, e.getKey());
      }
      return best;
    }

    if (count.get(nums[0]) == 1) best = Math.max(best, nums[0]);
    if (count.get(nums[n - 1]) == 1) best = Math.max(best, nums[n - 1]);
    return best;
  }
}
