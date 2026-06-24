import java.util.ArrayDeque;
import java.util.Deque;

public class Solution2398 {
  public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
    int n = chargeTimes.length;
    Deque<Integer> maxDeque = new ArrayDeque<>();
    long sumCost = 0;
    int left = 0;
    int best = 0;
    for (int right = 0; right < n; right++) {
      while (!maxDeque.isEmpty() && chargeTimes[maxDeque.peekLast()] <= chargeTimes[right]) {
        maxDeque.pollLast();
      }
      maxDeque.offerLast(right);
      sumCost += runningCosts[right];
      while (!maxDeque.isEmpty()) {
        long maxCharge = chargeTimes[maxDeque.peekFirst()];
        long k = right - left + 1;
        if (maxCharge + k * sumCost > budget) {
          if (maxDeque.peekFirst() == left) maxDeque.pollFirst();
          sumCost -= runningCosts[left];
          left++;
        } else {
          break;
        }
      }
      best = Math.max(best, right - left + 1);
    }
    return best;
  }
}
