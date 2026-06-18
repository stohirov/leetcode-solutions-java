import java.util.*;

public class Solution1167 {
  public int connectSticks(int[] sticks) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int s : sticks) pq.offer(s);
    int cost = 0;
    while (pq.size() > 1) {
      int a = pq.poll();
      int b = pq.poll();
      cost += a + b;
      pq.offer(a + b);
    }
    return cost;
  }
}
