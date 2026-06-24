import java.util.PriorityQueue;

public class Solution1675 {
  public int minimumDeviation(int[] nums) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    int min = Integer.MAX_VALUE;
    for (int n : nums) {
      int v = (n % 2 == 1) ? n * 2 : n;
      maxHeap.offer(v);
      min = Math.min(min, v);
    }

    int deviation = Integer.MAX_VALUE;
    while (true) {
      int max = maxHeap.poll();
      deviation = Math.min(deviation, max - min);
      if (max % 2 == 1) break;
      int halved = max / 2;
      min = Math.min(min, halved);
      maxHeap.offer(halved);
    }
    return deviation;
  }
}
