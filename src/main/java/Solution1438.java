import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1438 {
  public int longestSubarray(int[] nums, int limit) {
    Deque<Integer> maxDeque = new ArrayDeque<>();
    Deque<Integer> minDeque = new ArrayDeque<>();
    int left = 0;
    int best = 0;

    for (int right = 0; right < nums.length; right++) {
      while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[right]) {
        maxDeque.pollLast();
      }
      maxDeque.offerLast(nums[right]);
      while (!minDeque.isEmpty() && minDeque.peekLast() > nums[right]) {
        minDeque.pollLast();
      }
      minDeque.offerLast(nums[right]);

      while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
        if (maxDeque.peekFirst() == nums[left]) maxDeque.pollFirst();
        if (minDeque.peekFirst() == nums[left]) minDeque.pollFirst();
        left++;
      }
      best = Math.max(best, right - left + 1);
    }
    return best;
  }
}
