import java.util.*;

public class Solution1054 {
  public int[] rearrangeBarcodes(int[] barcodes) {
    Map<Integer, Integer> count = new HashMap<>();
    for (int b : barcodes) count.merge(b, 1, Integer::sum);
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    for (Map.Entry<Integer, Integer> e : count.entrySet()) {
      pq.offer(new int[] {e.getKey(), e.getValue()});
    }
    int[] res = new int[barcodes.length];
    int idx = 0;
    while (!pq.isEmpty()) {
      int[] first = pq.poll();
      if (idx == 0 || res[idx - 1] != first[0]) {
        res[idx++] = first[0];
        if (--first[1] > 0) pq.offer(first);
      } else {
        int[] second = pq.poll();
        res[idx++] = second[0];
        if (--second[1] > 0) pq.offer(second);
        pq.offer(first);
      }
    }
    return res;
  }
}
