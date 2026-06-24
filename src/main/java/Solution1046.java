import java.util.PriorityQueue;

public class Solution1046 {

  public int lastStoneWeight(int[] stones) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    for (int s : stones) {
      pq.offer(s);
    }
    while (pq.size() > 1) {
      int a = pq.poll();
      int b = pq.poll();
      if (a != b) {
        pq.offer(a - b);
      }
    }
    return pq.isEmpty() ? 0 : pq.poll();
  }
}
