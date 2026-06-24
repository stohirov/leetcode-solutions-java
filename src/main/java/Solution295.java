import java.util.Collections;
import java.util.PriorityQueue;

public class Solution295 {
  private final PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
  private final PriorityQueue<Integer> hi = new PriorityQueue<>();

  public Solution295() {
  }

  public void addNum(int num) {
    lo.offer(num);
    hi.offer(lo.poll());
    if (hi.size() > lo.size()) {
      lo.offer(hi.poll());
    }
  }

  public double findMedian() {
    if (lo.size() > hi.size()) {
      return lo.peek();
    }
    return (lo.peek() + hi.peek()) / 2.0;
  }
}
