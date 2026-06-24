import java.util.ArrayDeque;
import java.util.Deque;

public class Solution862 {

  public int shortestSubarray(int[] nums, int k) {
    int n = nums.length;
    long[] prefix = new long[n + 1];
    for (int i = 0; i < n; i++) {
      prefix[i + 1] = prefix[i] + nums[i];
    }

    int result = n + 1;
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i <= n; i++) {
      while (!deque.isEmpty() && prefix[i] - prefix[deque.peekFirst()] >= k) {
        result = Math.min(result, i - deque.pollFirst());
      }
      while (!deque.isEmpty() && prefix[deque.peekLast()] >= prefix[i]) {
        deque.pollLast();
      }
      deque.offerLast(i);
    }
    return result <= n ? result : -1;
  }
}
