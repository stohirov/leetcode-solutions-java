import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1425 {
  public int constrainedSubsetSum(int[] nums, int k) {
    int n = nums.length;
    int[] dp = new int[n];
    Deque<Integer> deque = new ArrayDeque<>();
    int best = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      while (!deque.isEmpty() && deque.peekFirst() < i - k) {
        deque.pollFirst();
      }
      int prevBest = deque.isEmpty() ? 0 : Math.max(0, dp[deque.peekFirst()]);
      dp[i] = nums[i] + prevBest;
      best = Math.max(best, dp[i]);
      while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
        deque.pollLast();
      }
      deque.offerLast(i);
    }
    return best;
  }
}
