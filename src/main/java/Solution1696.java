import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1696 {
  public int maxResult(int[] nums, int k) {
    int n = nums.length;
    int[] dp = new int[n];
    dp[0] = nums[0];
    Deque<Integer> deque = new ArrayDeque<>();
    deque.offerLast(0);

    for (int i = 1; i < n; i++) {
      while (!deque.isEmpty() && deque.peekFirst() < i - k) {
        deque.pollFirst();
      }
      dp[i] = dp[deque.peekFirst()] + nums[i];
      while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
        deque.pollLast();
      }
      deque.offerLast(i);
    }
    return dp[n - 1];
  }
}
