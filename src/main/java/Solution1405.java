import java.util.PriorityQueue;

public class Solution1405 {
  public String longestDiverseString(int a, int b, int c) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[1] - x[1]);
    if (a > 0) pq.offer(new int[] {'a', a});
    if (b > 0) pq.offer(new int[] {'b', b});
    if (c > 0) pq.offer(new int[] {'c', c});

    StringBuilder sb = new StringBuilder();
    while (!pq.isEmpty()) {
      int[] first = pq.poll();
      int len = sb.length();
      if (len >= 2 && sb.charAt(len - 1) == first[0] && sb.charAt(len - 2) == first[0]) {
        if (pq.isEmpty()) break;
        int[] second = pq.poll();
        sb.append((char) second[0]);
        if (--second[1] > 0) pq.offer(second);
        pq.offer(first);
      } else {
        sb.append((char) first[0]);
        if (--first[1] > 0) pq.offer(first);
      }
    }
    return sb.toString();
  }
}
