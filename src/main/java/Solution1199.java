import java.util.*;

public class Solution1199 {
  public int minBuildTime(int[] blocks, int split) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int b : blocks) pq.offer(b);
    while (pq.size() > 1) {
      int a = pq.poll();
      int b = pq.poll();
      pq.offer(b + split);
    }
    return pq.poll();
  }
}
