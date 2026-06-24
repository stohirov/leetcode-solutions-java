import java.util.PriorityQueue;

public class Solution2263 {
  public int convertArray(int[] nums) {
    return Math.min(cost(nums, false), cost(nums, true));
  }

  // Minimum total cost to make array non-decreasing (or non-increasing if reverse).
  // Uses the classic slope-trick / max-heap technique.
  private int cost(int[] nums, boolean reverse) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    long total = 0;
    for (int x : nums) {
      int v = reverse ? -x : x;
      pq.offer(v);
      if (pq.peek() > v) {
        total += pq.poll() - v;
        pq.offer(v);
      }
    }
    return (int) total;
  }
}
