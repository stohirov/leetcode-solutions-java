import java.util.HashMap;
import java.util.Map;

public class Solution3020 {

  public int maximumLength(int[] nums) {
    Map<Long, Integer> count = new HashMap<>();
    for (int num : nums) {
      count.merge((long) num, 1, Integer::sum);
    }

    int ans = 1;

    Integer ones = count.get(1L);
    if (ones != null) {
      ans = ones % 2 == 1 ? ones : ones - 1;
    }

    for (long x : count.keySet()) {
      if (x == 1) {
        continue;
      }
      int length = 0;
      long cur = x;
      while (count.getOrDefault(cur, 0) >= 2) {
        length += 2;
        cur = cur * cur;
      }
      length += count.getOrDefault(cur, 0) >= 1 ? 1 : -1;
      ans = Math.max(ans, length);
    }

    return ans;
  }
}
