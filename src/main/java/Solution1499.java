import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1499 {
  public int findMaxValueOfEquation(int[][] points, int k) {
    Deque<int[]> deque = new ArrayDeque<>();
    int best = Integer.MIN_VALUE;

    for (int[] p : points) {
      int x = p[0], y = p[1];
      while (!deque.isEmpty() && x - deque.peekFirst()[1] > k) {
        deque.pollFirst();
      }
      if (!deque.isEmpty()) {
        best = Math.max(best, y + x + deque.peekFirst()[0]);
      }
      int val = y - x;
      while (!deque.isEmpty() && deque.peekLast()[0] <= val) {
        deque.pollLast();
      }
      deque.offerLast(new int[] {val, x});
    }
    return best;
  }
}
