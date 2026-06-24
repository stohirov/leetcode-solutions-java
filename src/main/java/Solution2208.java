import java.util.PriorityQueue;

public class Solution2208 {
  public int halveArray(int[] nums) {
    PriorityQueue<Double> pq = new PriorityQueue<>((a, b) -> Double.compare(b, a));
    double total = 0;
    for (int num : nums) {
      total += num;
      pq.offer((double) num);
    }
    double target = total / 2;
    double reduced = 0;
    int ops = 0;
    while (reduced < target) {
      double largest = pq.poll();
      double half = largest / 2;
      reduced += half;
      pq.offer(half);
      ops++;
    }
    return ops;
  }
}
