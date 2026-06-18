import java.util.*;

public class Solution1262 {
  public int maxSumDivThree(int[] nums) {
    int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
    for (int num : nums) {
      int[] next = dp.clone();
      for (int r = 0; r < 3; r++) {
        if (dp[r] == Integer.MIN_VALUE) continue;
        int nr = (r + num) % 3;
        next[nr] = Math.max(next[nr], dp[r] + num);
      }
      dp = next;
    }
    return dp[0];
  }
}
