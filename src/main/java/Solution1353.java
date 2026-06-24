import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1353 {

  public int maxEvents(int[][] events) {
    Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int attended = 0, i = 0, n = events.length;
    int day = 0;
    while (i < n || !pq.isEmpty()) {
      if (pq.isEmpty()) {
        day = events[i][0];
      } else {
        day++;
      }

      while (i < n && events[i][0] <= day) {
        pq.offer(events[i][1]);
        i++;
      }

      while (!pq.isEmpty() && pq.peek() < day) pq.poll();
      if (!pq.isEmpty()) {
        pq.poll();
        attended++;
      }
    }
    return attended;
  }
}
